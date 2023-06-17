package com.ecommerce.wishlist.infrastructure.gateways;

import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.entities.Wishlist;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import com.ecommerce.wishlist.infrastructure.database.repository.ProductSchemaRepository;
import com.ecommerce.wishlist.infrastructure.database.repository.WishlistSchemaRepository;
import com.ecommerce.wishlist.infrastructure.database.schema.ProductSchema;
import com.ecommerce.wishlist.infrastructure.database.schema.WishlistSchema;
import com.ecommerce.wishlist.infrastructure.mapper.ProductModelMapper;
import com.ecommerce.wishlist.infrastructure.mapper.WishlistModelMapper;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@Component
public class WishlistDatabaseGateway implements WishlistGateway {

    private final MongoTemplate mongoTemplate;
    private final WishlistSchemaRepository wishlistSchemaRepository;
    private final ProductSchemaRepository productSchemaRepository;
    private final WishlistModelMapper wishlistModelMapper;
    private final ProductModelMapper productModelMapper;

    public WishlistDatabaseGateway(MongoTemplate mongoTemplate, WishlistSchemaRepository wishlistSchemaRepository, ProductSchemaRepository productSchemaRepository, WishlistModelMapper wishlistModelMapper, ProductModelMapper productModelMapper) {
        this.mongoTemplate = mongoTemplate;
        this.wishlistSchemaRepository = wishlistSchemaRepository;
        this.productSchemaRepository = productSchemaRepository;
        this.wishlistModelMapper = wishlistModelMapper;
        this.productModelMapper = productModelMapper;
    }

    @Override
    public void addProduct(UUID customerId, Product product) {
        WishlistSchema wishlistSchema = this.wishlistSchemaRepository
                .findByCustomerId(customerId)
                .orElseGet(() -> createWishlistSchema(customerId));

        ProductSchema productSchema = this.productSchemaRepository
                .findProductSchemaByProductId(product.getProductId())
                .orElseGet(() -> this.productSchemaRepository.save(this.productModelMapper.mapToSchema(product)));

        wishlistSchema.getProducts().add(productSchema);

        this.wishlistSchemaRepository.save(wishlistSchema);
    }

    private WishlistSchema createWishlistSchema(UUID customerId) {
        Wishlist wishlist = Wishlist
                .builder()
                .wishlistId(UUID.randomUUID())
                .customerId(customerId)
                .products(new HashSet<>())
                .build();

        return this.wishlistModelMapper.mapToSchema(wishlist);
    }

    @Override
    public void removeProduct(UUID customerId, UUID productId) {
        Query queryFindFromCustomerId = Query.query(Criteria.where("customerId").is(customerId));
        Query queryFindFromProductId = Query.query(Criteria.where("productId").is(productId));
        Update updateProductList = new Update().pull("products", queryFindFromProductId);

        this.mongoTemplate.upsert(queryFindFromCustomerId, updateProductList, WishlistSchema.class);
    }

    @Override
    public boolean customerWishlistContainsProduct(UUID customerId, UUID productId) {
        return this.wishlistSchemaRepository
                .findByCustomerIdAndProductId(customerId, productId)
                .isPresent();
    }

    @Override
    public Optional<Wishlist> findWishlistByCustomerId(UUID customerId) {
        return this.wishlistSchemaRepository
                .findByCustomerId(customerId)
                .map(this.wishlistModelMapper::mapToEntity);
    }
}

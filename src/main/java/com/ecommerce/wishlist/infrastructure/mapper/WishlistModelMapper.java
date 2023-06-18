package com.ecommerce.wishlist.infrastructure.mapper;

import com.ecommerce.wishlist.core.contract.ModelMapper;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.entities.Wishlist;
import com.ecommerce.wishlist.infrastructure.database.schema.ProductSchema;
import com.ecommerce.wishlist.infrastructure.database.schema.WishlistSchema;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class WishlistModelMapper implements ModelMapper<Wishlist, WishlistSchema> {

    private final ProductModelMapper productModelMapper;

    public WishlistModelMapper(ProductModelMapper productModelMapper) {
        this.productModelMapper = productModelMapper;
    }

    @Override
    public Wishlist mapToEntity(WishlistSchema wishlistSchema) {
        Set<Product> products = wishlistSchema
                .getProducts()
                .stream()
                .map(productModelMapper::mapToEntity)
                .collect(Collectors.toSet());

        return Wishlist
                .builder()
                .wishlistId(wishlistSchema.getWishlistId())
                .customerId(wishlistSchema.getCustomerId())
                .products(products)
                .build();
    }

    @Override
    public WishlistSchema mapToSchema(Wishlist wishlist) {
        Set<ProductSchema> products = wishlist
                .getProducts()
                .stream()
                .map(productModelMapper::mapToSchema)
                .collect(Collectors.toSet());

        return WishlistSchema
                .builder()
                .wishlistId(wishlist.getWishlistId())
                .customerId(wishlist.getCustomerId())
                .products(products)
                .build();
    }
}

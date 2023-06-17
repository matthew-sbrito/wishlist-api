package com.ecommerce.wishlist.infrastructure.database.repository;

import com.ecommerce.wishlist.infrastructure.database.schema.WishlistSchema;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface WishlistSchemaRepository extends MongoRepository<WishlistSchema, UUID> {
    @Query("{ 'customerId' : ?0 }")
    Optional<WishlistSchema> findByCustomerId(UUID customerId);

    @Query("{'customerId': ?0, 'products.productId': ?1 }")
    Optional<WishlistSchema> findByCustomerIdAndProductId(UUID customerId, UUID productId);
}

package com.ecommerce.wishlist.infrastructure.database.repository;

import com.ecommerce.wishlist.infrastructure.database.schema.ProductSchema;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductSchemaRepository extends MongoRepository<ProductSchema, UUID> {
    Optional<ProductSchema> findProductSchemaByProductId(UUID id);
}

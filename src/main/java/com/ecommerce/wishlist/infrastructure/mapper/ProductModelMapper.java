package com.ecommerce.wishlist.infrastructure.mapper;

import com.ecommerce.wishlist.core.contract.ModelMapper;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.infrastructure.database.schema.ProductSchema;
import org.springframework.stereotype.Component;

@Component
public class ProductModelMapper implements ModelMapper<Product, ProductSchema> {
    @Override
    public Product mapToEntity(ProductSchema productSchema) {
        return Product
                .builder()
                .productId(productSchema.getProductId())
                .name(productSchema.getName())
                .build();
    }

    @Override
    public ProductSchema mapToSchema(Product product) {
        return ProductSchema
                .builder()
                .productId(product.getProductId())
                .name(product.getName())
                .build();
    }
}

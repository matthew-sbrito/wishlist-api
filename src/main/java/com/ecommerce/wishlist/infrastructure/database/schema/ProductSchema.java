package com.ecommerce.wishlist.infrastructure.database.schema;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@Document(collection = "products")
public class ProductSchema {
    @Id
    private UUID productId;
    private String name;
}

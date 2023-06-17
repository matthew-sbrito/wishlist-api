package com.ecommerce.wishlist.infrastructure.database.schema;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
@Document(collection = "wishlists")
public class WishlistSchema {
    @Id
    private UUID wishlistId;
    @Indexed(unique = true)
    private UUID customerId;
    private Set<ProductSchema> products;
}
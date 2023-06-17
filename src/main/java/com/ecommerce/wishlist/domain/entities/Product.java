package com.ecommerce.wishlist.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class Product {
    private UUID productId;
    private String name;
}

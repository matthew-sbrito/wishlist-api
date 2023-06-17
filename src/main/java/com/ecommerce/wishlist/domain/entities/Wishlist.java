package com.ecommerce.wishlist.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class Wishlist {
    private UUID wishlistId;
    private UUID customerId;
    private Set<Product> products;
}

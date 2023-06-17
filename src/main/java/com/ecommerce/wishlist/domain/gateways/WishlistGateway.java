package com.ecommerce.wishlist.domain.gateways;

import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.entities.Wishlist;

import java.util.Optional;
import java.util.UUID;

public interface WishlistGateway {
    void addProduct(UUID customerId, Product product);
    void removeProduct(UUID customerId, UUID productId);
    boolean customerWishlistContainsProduct(UUID customerId, UUID productId);
    Optional<Wishlist> findWishlistByCustomerId(UUID customerId);
}

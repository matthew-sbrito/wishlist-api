package com.ecommerce.wishlist.domain.exceptions;

public class WishlistNotFoundException extends RuntimeException {
    public WishlistNotFoundException() {
        super("Wishlist not found!");
    }
}

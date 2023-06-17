package com.ecommerce.wishlist.domain.exceptions;

public class WishlistNotContainsProductException extends RuntimeException {
    public WishlistNotContainsProductException() {
        super("The customer doesn't have this product in your wishlist!");
    }
}

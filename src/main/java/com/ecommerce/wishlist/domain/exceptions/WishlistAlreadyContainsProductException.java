package com.ecommerce.wishlist.domain.exceptions;


public class WishlistAlreadyContainsProductException extends RuntimeException {
    public WishlistAlreadyContainsProductException() {
        super("The wishlist already contains product!");
    }
}

package com.ecommerce.wishlist.domain.exceptions;

import org.springframework.http.HttpStatus;

public class WishlistExceedsMaximumAllowedException extends RuntimeException {
    public WishlistExceedsMaximumAllowedException() {
        super("The customer already have 20 products in your wishlist!");
    }
}

package com.ecommerce.wishlist.domain.exceptions;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class WishlistNotContainsProductException extends HttpResponseException {
    public static final String MESSAGE_EXCEPTION = "The customer doesn't have this product in your wishlist!";

    public WishlistNotContainsProductException() {
        super(HttpStatus.NOT_FOUND, MESSAGE_EXCEPTION);
    }
}

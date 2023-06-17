package com.ecommerce.wishlist.domain.exceptions;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class WishlistNotFoundException extends HttpResponseException {
    public static final String MESSAGE_EXCEPTION = "The wishlist not found!";
    public WishlistNotFoundException() {
        super(HttpStatus.NOT_FOUND, MESSAGE_EXCEPTION);
    }
}

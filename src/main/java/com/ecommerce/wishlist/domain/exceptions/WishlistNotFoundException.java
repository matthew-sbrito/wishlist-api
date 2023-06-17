package com.ecommerce.wishlist.domain.exceptions;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class WishlistNotFoundException extends HttpResponseException {
    public WishlistNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Wishlist not found!");
    }
}

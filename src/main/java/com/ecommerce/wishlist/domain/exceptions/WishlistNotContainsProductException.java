package com.ecommerce.wishlist.domain.exceptions;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class WishlistNotContainsProductException extends HttpResponseException {
    public WishlistNotContainsProductException() {
        super(HttpStatus.NOT_FOUND, "The customer doesn't have this product in your wishlist!");
    }
}

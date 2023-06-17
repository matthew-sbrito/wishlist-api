package com.ecommerce.wishlist.domain.exceptions;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class WishlistAlreadyContainsProductException extends HttpResponseException {
    public static final String MESSAGE_EXCEPTION = "The wishlist already contains product!";
    public WishlistAlreadyContainsProductException() {
        super(HttpStatus.CONFLICT, MESSAGE_EXCEPTION);
    }
}

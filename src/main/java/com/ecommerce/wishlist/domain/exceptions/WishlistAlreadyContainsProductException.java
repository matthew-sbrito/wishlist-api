package com.ecommerce.wishlist.domain.exceptions;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class WishlistAlreadyContainsProductException extends HttpResponseException {
    public WishlistAlreadyContainsProductException() {
        super(HttpStatus.CONFLICT, "The wishlist already contains product!");
    }
}

package com.ecommerce.wishlist.domain.exceptions;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import org.springframework.http.HttpStatus;

public class WishlistExceedsMaximumAllowedException extends HttpResponseException {
    public WishlistExceedsMaximumAllowedException() {
        super(HttpStatus.BAD_REQUEST, "The customer already have 20 products in your wishlist!");
    }
}

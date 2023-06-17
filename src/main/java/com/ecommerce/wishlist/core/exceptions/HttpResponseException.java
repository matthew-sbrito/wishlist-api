package com.ecommerce.wishlist.core.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpResponseException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String reason;

    public HttpResponseException(HttpStatus httpStatus, String reason) {
        super(reason);

        this.httpStatus = httpStatus;
        this.reason = reason;
    }
}

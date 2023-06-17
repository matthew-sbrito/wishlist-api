package com.ecommerce.wishlist.core.advices;

import com.ecommerce.wishlist.core.exceptions.HttpResponseException;
import com.ecommerce.wishlist.core.exceptions.HttpResponseExceptionViewModel;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@Slf4j
@ControllerAdvice
public class HttpResponseExceptionHandler {
    @ExceptionHandler(HttpResponseException.class)
    protected ResponseEntity<HttpResponseExceptionViewModel> handleHttpError(HttpResponseException httpStatusException, HttpServletRequest request) {
        int status = httpStatusException.getHttpStatus().value();
        String reasonPhrase = httpStatusException.getHttpStatus().getReasonPhrase();
        String reason = httpStatusException.getReason();

        LOGGER.warn("A error occurred with status {} [{}] and reason '{}'", status, reasonPhrase, reason);

        HttpResponseExceptionViewModel error = HttpResponseExceptionViewModel
                .builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .error(reasonPhrase)
                .reason(reason)
                .path(request.getRequestURI())
                .build();

        return ResponseEntity
                .status(httpStatusException.getHttpStatus())
                .body(error);
    }

}

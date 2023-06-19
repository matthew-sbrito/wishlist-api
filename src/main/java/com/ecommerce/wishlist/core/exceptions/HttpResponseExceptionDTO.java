package com.ecommerce.wishlist.core.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class HttpResponseExceptionDTO {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String reason;
    private String path;
}

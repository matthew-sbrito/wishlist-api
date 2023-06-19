package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.core.exceptions.HttpResponseExceptionDTO;
import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase.Input;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RestController
@Tag(name = "Remove", description = "Controller for remove a product from the customer's wishlist")
public class RemoveProductToCustomerWishlistController {

    private final RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase;

    public RemoveProductToCustomerWishlistController(RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase) {
        this.removeProductFromCustomerWishlistUseCase = removeProductFromCustomerWishlistUseCase;
    }

    @Operation(
            summary = "Remove a product from customer's wishlist",
            responses = {
                    @ApiResponse(responseCode = "200", description = "The product has been successfully removed "),
                    @ApiResponse(responseCode = "404", description = "The product not contains in the customer's wishlist", content = {
                            @Content(schema = @Schema(oneOf = HttpResponseExceptionDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
                    })
            }
    )
    @DeleteMapping("/wishlist/{customerId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void handle(@PathVariable UUID customerId, @PathVariable UUID productId) {
        LOGGER.info("Request to remove product in customer's wishlist!");
        LOGGER.info("Remove product with id '{}' from customer with id '{}'", productId, customerId);

        removeProductFromCustomerWishlistUseCase
                .execute(new Input(customerId, productId));
    }
}

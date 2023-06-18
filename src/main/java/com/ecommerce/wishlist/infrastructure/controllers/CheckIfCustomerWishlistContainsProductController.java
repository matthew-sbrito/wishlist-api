package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.domain.usecases.CheckIfCustomerWishlistContainsProductUseCase;
import com.ecommerce.wishlist.domain.usecases.CheckIfCustomerWishlistContainsProductUseCase.Input;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
@Tag(name = "Check", description = "Controller for check if a product contains in the customer's wishlist")
public class CheckIfCustomerWishlistContainsProductController {
    private final CheckIfCustomerWishlistContainsProductUseCase checkIfCustomerWishlistContainsProductUseCase;

    public CheckIfCustomerWishlistContainsProductController(CheckIfCustomerWishlistContainsProductUseCase checkIfCustomerWishlistContainsProductUseCase) {
        this.checkIfCustomerWishlistContainsProductUseCase = checkIfCustomerWishlistContainsProductUseCase;
    }

    @Operation(summary = "Add a product to customer's wishlist")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "The product contains in the customer's wishlist"),
            @ApiResponse(responseCode = "404", description = "The product not contains in the customer's wishlist")
    })
    @GetMapping("/wishlist/{customerId}/products/{productId}/contains")
    @ResponseStatus(HttpStatus.OK)
    public void handle(@PathVariable UUID customerId, @PathVariable UUID productId) {
        LOGGER.info("Request to check if customer's wishlist contains product!");
        LOGGER.info("Check product with id '{}' on customer's wishlist with id '{}'", productId, customerId);

        checkIfCustomerWishlistContainsProductUseCase.execute(
                new Input(customerId, productId)
        );
    }
}

package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.domain.usecases.CheckIfCustomerWishlistContainsProductUseCase;
import com.ecommerce.wishlist.domain.usecases.CheckIfCustomerWishlistContainsProductUseCase.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@Slf4j
@RestController
public class CheckIfCustomerWishlistContainsProductController {
    private final CheckIfCustomerWishlistContainsProductUseCase checkIfCustomerWishlistContainsProductUseCase;

    public CheckIfCustomerWishlistContainsProductController(CheckIfCustomerWishlistContainsProductUseCase checkIfCustomerWishlistContainsProductUseCase) {
        this.checkIfCustomerWishlistContainsProductUseCase = checkIfCustomerWishlistContainsProductUseCase;
    }

    @GetMapping("/wishlist/{customerId}/products/{productId}/contains")
    @ResponseStatus(HttpStatus.OK)
    public void handle(@PathVariable UUID customerId, @PathVariable UUID productId) {
        LOGGER.info("Request to check if customer's wishlist contains product!");
        LOGGER.info("Check product with id '{}' on customer's wishlist with id '{}'", productId, customerId);

        this.checkIfCustomerWishlistContainsProductUseCase.execute(
                new Input(customerId, productId)
        );
    }
}

package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@Slf4j
@RestController
public class RemoveProductToCustomerWishListController {

    private final RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase;

    public RemoveProductToCustomerWishListController(RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase) {
        this.removeProductFromCustomerWishlistUseCase = removeProductFromCustomerWishlistUseCase;
    }

    @DeleteMapping("/wishlist/{customerId}/products/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void handle(@PathVariable UUID customerId, @PathVariable UUID productId) {
        LOGGER.info("Request to remove product in customer's wishlist!");
        LOGGER.info("Remove product with id '{}' from customer with id '{}'", productId, customerId);

        this.removeProductFromCustomerWishlistUseCase
                .execute(new Input(customerId, productId));
    }
}

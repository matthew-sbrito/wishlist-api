package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@Slf4j
@RestController
public class RemoveProductToCustomerWishListController {

    private final RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase;

    public RemoveProductToCustomerWishListController(RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase) {
        this.removeProductFromCustomerWishlistUseCase = removeProductFromCustomerWishlistUseCase;
    }

    @DeleteMapping("/wishlist/product")
    @ResponseStatus(HttpStatus.OK)
    public void handle(@RequestBody @Validated Request request) {
        LOGGER.info("Request to remove product in customer's wishlist!");

        this.removeProductFromCustomerWishlistUseCase
                .execute(new Input(request.customerId(), request.productId()));
    }

    public record Request(UUID customerId, UUID productId) { }
}

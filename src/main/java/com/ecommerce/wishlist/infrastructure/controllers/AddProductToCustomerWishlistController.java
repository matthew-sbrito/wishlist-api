package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.usecases.AddProductToCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.AddProductToCustomerWishlistUseCase.Input;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
public class AddProductToCustomerWishlistController {

    private final AddProductToCustomerWishlistUseCase addProductToCustomerWishlistUseCase;

    public AddProductToCustomerWishlistController(AddProductToCustomerWishlistUseCase addProductToCustomerWishlistUseCase) {
        this.addProductToCustomerWishlistUseCase = addProductToCustomerWishlistUseCase;
    }

    @PostMapping("/wishlist/{customerId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void handle(@PathVariable UUID customerId, @RequestBody Product request) {
        LOGGER.info("Request to add product in customer's wishlist!");

        this.addProductToCustomerWishlistUseCase
                .execute(new Input(customerId, request));
    }
}

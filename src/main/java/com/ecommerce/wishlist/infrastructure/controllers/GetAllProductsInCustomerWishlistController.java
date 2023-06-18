package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.usecases.GetAllProductsInCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.GetAllProductsInCustomerWishlistUseCase.Input;
import com.ecommerce.wishlist.domain.usecases.GetAllProductsInCustomerWishlistUseCase.Output;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
public class GetAllProductsInCustomerWishlistController {
    private final GetAllProductsInCustomerWishlistUseCase getAllProductsInCustomerWishlistUseCase;

    public GetAllProductsInCustomerWishlistController(GetAllProductsInCustomerWishlistUseCase getAllProductsInCustomerWishlistUseCase) {
        this.getAllProductsInCustomerWishlistUseCase = getAllProductsInCustomerWishlistUseCase;
    }

    @GetMapping("/wishlist/{customerId}/products")
    @ResponseStatus(HttpStatus.OK)
    public Response handle(@PathVariable UUID customerId) {
        LOGGER.info("Request to get all products from customer's wishlist!");
        LOGGER.info("Get all products from customer's wishlist with id '{}'", customerId);

        Output output = getAllProductsInCustomerWishlistUseCase.execute(
                new Input(customerId)
        );

        return new Response(output.products());
    }

    public record Response(List<Product> products) { }
}

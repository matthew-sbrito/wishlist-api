package com.ecommerce.wishlist.infrastructure.controllers;

import com.ecommerce.wishlist.core.exceptions.HttpResponseExceptionDTO;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.usecases.AddProductToCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.AddProductToCustomerWishlistUseCase.Input;
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
@Tag(name = "Add", description = "Controller for add a product to customer's wishlist")
public class AddProductToCustomerWishlistController {

    private final AddProductToCustomerWishlistUseCase addProductToCustomerWishlistUseCase;

    public AddProductToCustomerWishlistController(AddProductToCustomerWishlistUseCase addProductToCustomerWishlistUseCase) {
        this.addProductToCustomerWishlistUseCase = addProductToCustomerWishlistUseCase;
    }

    @Operation(
            summary = "Add a product to customer's wishlist",
            responses = {
                    @ApiResponse(responseCode = "201", description = "The product has been added to the customer's wishlist"),
                    @ApiResponse(responseCode = "400", description = "The customer's wishlist already contains the maximum allowed of products", content = {
                            @Content(schema = @Schema(oneOf = HttpResponseExceptionDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
                    }),
                    @ApiResponse(responseCode = "409", description = "The product is already contains on the customer's wishlist", content = {
                            @Content(schema = @Schema(oneOf = HttpResponseExceptionDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE)
                    })
            }
    )
    @PostMapping("/wishlist/{customerId}/products")
    @ResponseStatus(HttpStatus.CREATED)
    public void handle(@PathVariable UUID customerId, @RequestBody Product request) {
        LOGGER.info("Request to add product in customer's wishlist!");
        LOGGER.info("Add product in wishlist from customer with id '{}'", customerId);

        addProductToCustomerWishlistUseCase
                .execute(new Input(customerId, request));
    }
}

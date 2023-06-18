package com.ecommerce.wishlist.infrastructure.controller;

import com.ecommerce.wishlist.core.ProductFactory;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.exceptions.WishlistAlreadyContainsProductException;
import com.ecommerce.wishlist.domain.exceptions.WishlistExceedsMaximumAllowedException;
import com.ecommerce.wishlist.infrastructure.configuration.ControllerBaseTests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AddProductToCostumerWishlistControllerTests extends ControllerBaseTests {
    @Test
    @DisplayName("Should be added the product to the customer's wishlist and return status 201")
    void shouldBeAddedProductToCostumerWishlistAndReturnStatus201() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .post(String.format("/wishlist/%s/products", customerId))
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should not add the product to the customer's wishlist and should return status 409 as the product has already been added")
    void shouldNotBeAddProductToCostumerWishlistAndReturnStatus409() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .post(String.format("/wishlist/%s/products", customerId))
                .then()
                .statusCode(HttpStatus.CREATED.value());

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .post(String.format("/wishlist/%s/products", customerId))
                .then()
                .statusCode(HttpStatus.CONFLICT.value())
                .extract()
                .response();

        String reason = response.jsonPath().get("reason");

        assertThat(reason)
                .isEqualTo(WishlistAlreadyContainsProductException.MESSAGE_EXCEPTION);
    }

    @Test
    @DisplayName("Should not allow the insertion of more products than the maximum allowed in the wishlist and return status 400")
    void shouldNotAllowInsertMoreProductThanMaximumAllowedAndReturnStatus400() {
        List<Product> products = ProductFactory.getMaximumProductList();

        for (Product product: products) {
            RestAssured.given()
                    .contentType(ContentType.JSON)
                    .body(product)
                    .post(String.format("/wishlist/%s/products", customerId))
                    .then()
                    .statusCode(HttpStatus.CREATED.value());
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .post(String.format("/wishlist/%s/products", customerId))
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .extract()
                .response();

        String reason = response.jsonPath().get("reason");

        assertThat(reason)
                .isEqualTo(WishlistExceedsMaximumAllowedException.MESSAGE_EXCEPTION);
    }
}

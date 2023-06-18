package com.ecommerce.wishlist.infrastructure.controller;

import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotFoundException;
import com.ecommerce.wishlist.infrastructure.configuration.ControllerBaseTests;
import com.ecommerce.wishlist.infrastructure.controllers.GetAllProductsInCustomerWishlistController;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class GetAllProductsInCustomerWishlistControllerTests extends ControllerBaseTests {

    @BeforeEach
    void addProductInCustomerWishlist() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(product)
                .post(String.format("/wishlist/%s/products", customerId))
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should be returned the product list from the customer's wishlist and return status 200")
    void shouldBeReturnedProductListFromCustomerWishlistAndStatus200() {
        Response response = RestAssured.given()
                .get(String.format("/wishlist/%s/products", customerId))
                .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .response();

        List<Product> products = response
                .getBody()
                .as(GetAllProductsInCustomerWishlistController.Response.class)
                .products();

        assertThat( products.size())
                .isEqualTo(1);

        assertThat(products.get(0))
                .isEqualTo(product);
    }

    @Test
    @DisplayName("Should be returned status 404 because the customer doesn't have wishlist")
    void shouldBeReturnedStatus404CustomerDoesNotHaveWishlist() {
        UUID randomUserId = UUID.randomUUID();

        Response response = RestAssured.given()
                .get(String.format("/wishlist/%s/products", randomUserId))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .response();

        String reason = response.jsonPath().get("reason");

        assertThat(reason)
                .isEqualTo(WishlistNotFoundException.MESSAGE_EXCEPTION);
    }
}

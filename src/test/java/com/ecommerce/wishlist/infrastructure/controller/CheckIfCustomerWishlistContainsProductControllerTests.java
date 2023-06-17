package com.ecommerce.wishlist.infrastructure.controller;

import com.ecommerce.wishlist.core.ProductFactory;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotContainsProductException;
import com.ecommerce.wishlist.infrastructure.configuration.ControllerBaseTests;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckIfCustomerWishlistContainsProductControllerTests extends ControllerBaseTests {

    @BeforeEach
    void addProductInCustomerWishlist() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .body(this.product)
                .post(String.format("/wishlist/%s/products", this.customerId))
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Should be checked if the customer's wishlist contains product and return status 200")
    void shouldBeCheckedIfCustomerWishlistContainsProductAndReturnStatus200() {
        RestAssured.given()
                .get(String.format("/wishlist/%s/products/%s/contains", this.customerId, this.product.getProductId()))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should be checked if the customer's wishlist not contains product and return status 404")
    void shouldBeCheckedIfCustomerWishlistNotContainsProductAndReturnStatus404() {
        Product randomProduct = ProductFactory
                .createRandomProduct();

        Response response = RestAssured.given()
                .get(String.format("/wishlist/%s/products/%s/contains", this.customerId, randomProduct.getProductId()))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .response();

        String reason = response.jsonPath().get("reason");

        assertThat(reason)
                .isEqualTo(WishlistNotContainsProductException.MESSAGE_EXCEPTION);
    }
}

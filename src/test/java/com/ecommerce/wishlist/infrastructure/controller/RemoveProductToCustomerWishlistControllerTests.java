package com.ecommerce.wishlist.infrastructure.controller;

import com.ecommerce.wishlist.core.ProductFactory;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotContainsProductException;
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

import static org.assertj.core.api.Assertions.assertThat;

public class RemoveProductToCustomerWishlistControllerTests extends ControllerBaseTests {
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
    @DisplayName("Should be remove product from customer wishlist, return status 200 and check size equals 0")
    void shouldBeRemoveProductFromCustomerWishlistReturnStatus200AndCheckSizeEquals0() {
        RestAssured.given()
                .contentType(ContentType.JSON)
                .delete(String.format("/wishlist/%s/products/%s", customerId, product.getProductId()))
                .then()
                .statusCode(HttpStatus.OK.value());

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

        assertThat(products.size())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("Should not remove the product because it is not on the customer's wishlist")
    void shouldNotRemoveProductNotContainsOnCustomerWishlist() {
        Product product = ProductFactory.createRandomProduct();

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .delete(String.format("/wishlist/%s/products/%s", customerId, product.getProductId()))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .extract()
                .response();

        String reason = response.jsonPath().get("reason");

        assertThat(reason)
                .isEqualTo(WishlistNotContainsProductException.MESSAGE_EXCEPTION);
    }
}

package com.ecommerce.wishlist.infrastructure.configuration;

import com.ecommerce.wishlist.core.ProductFactory;
import com.ecommerce.wishlist.domain.entities.Product;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class ControllerBaseTests extends MongoContainerBaseTests {

    @LocalServerPort
    private int randomPort;
    protected UUID customerId;
    protected Product product;

    @BeforeEach
    void setUpTest() {
        RestAssured.port = randomPort;
    }

    @BeforeEach
    void setUpParams() {
        this.customerId = UUID.randomUUID();
        this.product = ProductFactory.createRandomProduct();
    }
}

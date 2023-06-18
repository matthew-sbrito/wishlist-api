package com.ecommerce.wishlist.domain.configuration;

import com.ecommerce.wishlist.core.ProductFactory;
import com.ecommerce.wishlist.domain.entities.Product;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public abstract class UseCaseBaseTests {
    protected UUID randomCustomerId;
    protected UUID randomWishlistId;
    protected Product randomProduct;

    @Before
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    void setUpParams() {
        randomWishlistId = UUID.randomUUID();
        randomCustomerId = UUID.randomUUID();
        randomProduct = ProductFactory.createRandomProduct();
    }

}

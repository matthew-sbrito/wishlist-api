package com.ecommerce.wishlist.domain.usecase;

import com.ecommerce.wishlist.core.ProductFactory;
import com.ecommerce.wishlist.domain.configuration.UseCaseBaseTests;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.entities.Wishlist;
import com.ecommerce.wishlist.domain.exceptions.WishlistAlreadyContainsProductException;
import com.ecommerce.wishlist.domain.exceptions.WishlistExceedsMaximumAllowedException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import com.ecommerce.wishlist.domain.usecases.AddProductToCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.AddProductToCustomerWishlistUseCase.Input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class AddProductToCostumerWishlistUseCaseTests extends UseCaseBaseTests {
    @InjectMocks
    AddProductToCustomerWishlistUseCase addProductToCustomerWishlistUseCase;
    @Mock
    WishlistGateway wishlistGateway;
    UUID randomWishlistId = UUID.randomUUID();
    UUID randomCustomerId = UUID.randomUUID();
    Product product = ProductFactory.createRandomProduct();

    @Test
    @DisplayName("Should be added a product to the costumer's wishlist")
    void shouldBeAddedProductToCustomerWishlist() {
        Input input = new Input(randomCustomerId, product);

        assertDoesNotThrow(() -> addProductToCustomerWishlistUseCase.execute(input));

        verify(wishlistGateway, times(1))
                .findWishlistByCustomerId(randomCustomerId);

        verify(wishlistGateway, times(1))
                .addProduct(randomCustomerId, product);
    }

    @Test
    @DisplayName("Should throw an exception when trying add a product that has already been added to the customer's wishlist")
    void shouldThrowExceptionWhenTryingAddProductThatHasAlreadyBeenAddedToCostumerWishlist() {
        Wishlist wishlistSchema = Wishlist
                .builder()
                .wishlistId(randomWishlistId)
                .customerId(randomCustomerId)
                .products(new HashSet<>(List.of(product)))
                .build();

        when(wishlistGateway.findWishlistByCustomerId(randomCustomerId))
                .thenReturn(Optional.of(wishlistSchema));

        Input input = new Input(randomCustomerId, product);

        assertThrows(
                WishlistAlreadyContainsProductException.class,
                () -> addProductToCustomerWishlistUseCase.execute(input)
        );
    }

    @Test
    @DisplayName("Should throw an exception when trying add a product to the customer's wishlist which is full")
    void shouldThrowExceptionWhenTryingAddProductToCustomerWishlistIsFull() {
        List<Product> products = ProductFactory.getMaximumProductList();

        Wishlist wishlist = Wishlist
                .builder()
                .wishlistId(randomWishlistId)
                .customerId(randomCustomerId)
                .products(new HashSet<>(products))
                .build();

        when(wishlistGateway.findWishlistByCustomerId(randomCustomerId))
                .thenReturn(Optional.of(wishlist));

        assertThrows(
                WishlistExceedsMaximumAllowedException.class,
                () -> addProductToCustomerWishlistUseCase.execute(new Input(randomCustomerId, product))
        );
    }

}

package com.ecommerce.wishlist.domain.usecase;

import com.ecommerce.wishlist.domain.configuration.UseCaseBaseTests;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotContainsProductException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import com.ecommerce.wishlist.domain.usecases.CheckIfCustomerWishlistContainsProductUseCase;
import com.ecommerce.wishlist.domain.usecases.CheckIfCustomerWishlistContainsProductUseCase.Input;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CheckIfCustomerWishlistContainsProductUseCaseTests extends UseCaseBaseTests {
    @InjectMocks
    CheckIfCustomerWishlistContainsProductUseCase checkIfCustomerWishlistContainsProductUseCase;
    @Mock
    WishlistGateway wishlistGateway;

    @Test
    @DisplayName("Should be checked if the customer's wishlist contains product")
    void shouldBeCheckedIfCustomerWishlistContainsProduct() {
        Input input = new Input(randomCustomerId, randomProduct.getProductId());

        when(wishlistGateway.customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId()))
                .thenReturn(true);

        assertDoesNotThrow(() -> checkIfCustomerWishlistContainsProductUseCase.execute(input));

        verify(wishlistGateway, times(1))
                .customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId());
    }

    @Test
    @DisplayName("Should throw an exception when trying to check if the customer's wishlist contains a product they don't have")
    void shouldThrowExceptionWhenTryingCheckUfCustomerWishlistContainsProduct() {
        Input input = new Input(randomCustomerId, randomProduct.getProductId());

        when(wishlistGateway.customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId()))
                .thenReturn(false);

        assertThrows(
                WishlistNotContainsProductException.class,
                () -> checkIfCustomerWishlistContainsProductUseCase.execute(input)
        );

        verify(wishlistGateway, times(1))
                .customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId());
    }


}

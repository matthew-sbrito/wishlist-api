package com.ecommerce.wishlist.domain.usecase;

import com.ecommerce.wishlist.domain.configuration.UseCaseBaseTests;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotContainsProductException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.RemoveProductFromCustomerWishlistUseCase.Input;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class RemoveProductFromCustomerWishlistUseCaseTest extends UseCaseBaseTests {
    @InjectMocks
    RemoveProductFromCustomerWishlistUseCase removeProductFromCustomerWishlistUseCase;
    @Mock
    WishlistGateway wishlistGateway;

    @Test
    @DisplayName("Should be remove product from customer wishlist, return status 200 and check size equals 0")
    void shouldBeRemoveProductFromCustomerWishlist() {
        Input input = new Input(randomCustomerId, randomProduct.getProductId());

        when(wishlistGateway.customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId()))
                .thenReturn(true);

        assertDoesNotThrow(() -> removeProductFromCustomerWishlistUseCase.execute(input));

        verify(wishlistGateway, times(1))
                .customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId());
    }

    @Test
    @DisplayName("Should throw an exception when trying to remove the product that is not in the customer's wishlist")
    void shouldThrowExceptionWhenTryingRemoveProductNotInCustomerWishlist() {
        Input input = new Input(randomCustomerId, randomProduct.getProductId());

        when(wishlistGateway.customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId()))
                .thenReturn(false);

        assertThrows(
                WishlistNotContainsProductException.class,
                () -> removeProductFromCustomerWishlistUseCase.execute(input)
        );

        verify(wishlistGateway, times(1))
                .customerWishlistContainsProduct(randomCustomerId, randomProduct.getProductId());
    }

}

package com.ecommerce.wishlist.domain.usecase;

import com.ecommerce.wishlist.core.ProductFactory;
import com.ecommerce.wishlist.domain.configuration.UseCaseBaseTests;
import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.entities.Wishlist;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotFoundException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import com.ecommerce.wishlist.domain.usecases.GetAllProductsInCustomerWishlistUseCase;
import com.ecommerce.wishlist.domain.usecases.GetAllProductsInCustomerWishlistUseCase.Input;
import com.ecommerce.wishlist.domain.usecases.GetAllProductsInCustomerWishlistUseCase.Output;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class GetAllProductsInCustomerWishlistUseCaseTest extends UseCaseBaseTests {
    @InjectMocks
    GetAllProductsInCustomerWishlistUseCase getAllProductsInCustomerWishlistUseCase;
    @Mock
    WishlistGateway wishlistGateway;

    @Test
    @DisplayName("Should get all products from customer's wishlist")
    void shouldGetAllProductsFromCustomerWishlist() {
        List<Product> products = ProductFactory.getMaximumProductList();

        Wishlist wishlist = Wishlist
                .builder()
                .wishlistId(randomWishlistId)
                .customerId(randomCustomerId)
                .products(new HashSet<>(products))
                .build();

        when(wishlistGateway.findWishlistByCustomerId(randomCustomerId))
                .thenReturn(Optional.of(wishlist));

        Input input = new Input(randomCustomerId);

        Output output = assertDoesNotThrow(() -> getAllProductsInCustomerWishlistUseCase.execute(input));

        assertThat(output.products().size())
                .isEqualTo(products.size());

        verify(wishlistGateway, times(1))
                .findWishlistByCustomerId(randomCustomerId);
    }

    @Test
    @DisplayName("Should throw an exception when trying get all products and customer's wishlist not exists")
    void shouldThrowExceptionWhenTryingGetAllProductAndCustomerWishlistNotExists() {
        Input input = new Input(randomCustomerId);

        assertThrows(
                WishlistNotFoundException.class,
                () -> getAllProductsInCustomerWishlistUseCase.execute(input)
         );

        verify(wishlistGateway, times(1))
                .findWishlistByCustomerId(randomCustomerId);
    }

}

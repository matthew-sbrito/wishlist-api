package com.ecommerce.wishlist.domain.usecases;

import com.ecommerce.wishlist.domain.exceptions.WishlistNotContainsProductException;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotFoundException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveProductFromCustomerWishlistUseCase {

    private final WishlistGateway wishlistGateway;

    public RemoveProductFromCustomerWishlistUseCase(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    public void execute(Input input) throws WishlistNotFoundException {
        boolean containsProduct = wishlistGateway
                .customerWishlistContainsProduct(input.customerId(), input.productId());

        if(!containsProduct) {
            throw new WishlistNotContainsProductException();
        }

        wishlistGateway.removeProduct(input.customerId(), input.productId());
    }

    public record Input(UUID customerId, UUID productId) {  }
}

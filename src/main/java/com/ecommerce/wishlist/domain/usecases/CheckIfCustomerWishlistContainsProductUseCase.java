package com.ecommerce.wishlist.domain.usecases;

import com.ecommerce.wishlist.domain.exceptions.WishlistNotContainsProductException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CheckIfCustomerWishlistContainsProductUseCase {

    private final WishlistGateway wishlistGateway;

    public CheckIfCustomerWishlistContainsProductUseCase(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    public void execute(Input input) {
        boolean containsProduct = this.wishlistGateway
                .customerWishlistContainsProduct(input.customerId(), input.productId());

        if(!containsProduct)
            throw new WishlistNotContainsProductException();
    }

    public record Input(UUID customerId, UUID productId) { }
}

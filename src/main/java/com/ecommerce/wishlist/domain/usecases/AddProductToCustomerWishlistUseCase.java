package com.ecommerce.wishlist.domain.usecases;

import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.entities.Wishlist;
import com.ecommerce.wishlist.domain.exceptions.WishlistAlreadyContainsProductException;
import com.ecommerce.wishlist.domain.exceptions.WishlistExceedsMaximumAllowedException;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotFoundException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddProductToCustomerWishlistUseCase {

    private static final int MAXIMUM_PRODUCTS_ALLOWED = 20;

    private final WishlistGateway wishlistGateway;

    public AddProductToCustomerWishlistUseCase(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    public void execute(Input input) throws WishlistNotFoundException {
        Optional<Wishlist> wishlistOptional = this.wishlistGateway
                .findWishlistByCustomerId(input.customerId());

        if(wishlistOptional.isEmpty()) {
            this.wishlistGateway.addProduct(input.customerId(), input.product());
            return;
        }

        Wishlist wishlist = wishlistOptional.get();

        if(wishlist.getProducts().contains(input.product())) {
            throw new WishlistAlreadyContainsProductException();
        }

        if(wishlist.getProducts().size() >= MAXIMUM_PRODUCTS_ALLOWED) {
            throw new WishlistExceedsMaximumAllowedException();
        }

        this.wishlistGateway.addProduct(input.customerId(), input.product());
    }

    public record Input(UUID customerId, Product product) {  }
}

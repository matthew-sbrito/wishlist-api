package com.ecommerce.wishlist.domain.usecases;

import com.ecommerce.wishlist.domain.entities.Product;
import com.ecommerce.wishlist.domain.entities.Wishlist;
import com.ecommerce.wishlist.domain.exceptions.WishlistNotFoundException;
import com.ecommerce.wishlist.domain.gateways.WishlistGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetAllProductsInCustomerWishlistUseCase {

    private final WishlistGateway wishlistGateway;

    public GetAllProductsInCustomerWishlistUseCase(WishlistGateway wishlistGateway) {
        this.wishlistGateway = wishlistGateway;
    }

    public Output execute(Input input) throws WishlistNotFoundException {
        Wishlist wishlist = this.wishlistGateway.findWishlistByCustomerId(input.customerId())
                .orElseThrow(WishlistNotFoundException::new);

        return new Output(wishlist.getProducts().stream().toList());
    }

    public record Input(UUID customerId) { }
    public record Output(List<Product> products) { }
}

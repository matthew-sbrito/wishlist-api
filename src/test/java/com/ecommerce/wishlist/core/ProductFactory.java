package com.ecommerce.wishlist.core;

import com.ecommerce.wishlist.domain.entities.Product;

import java.util.UUID;
import java.util.List;

public abstract class ProductFactory {
    private static final List<String> PRODUCT_NAME_LIST = List.of(
            "Tênis Mizuno Wave Titan 2",
            "Tênis Adidas Breaknet",
            "Tênis Nike Downshifter",
            "Chinelo infatil Catargo",
            "Tênis Puma Softride",
            "Tênis Skechers Microspec",
            "Tênis Skechers Go Run Fast Earthy",
            "Mochila Infatil Nike",
            "Kit Meia Puma",
            "Boné Juvenil Adidas Dance",
            "Chuteira Futsal Nike",
            "Camiseta Oakley O-Bark",
            "Camisa Real Madrid Home",
            "Calça Moletom Nike NSW Club JSY",
            "Camiseta Adidas Essentials Base",
            "Tênis Oakle Stratus",
            "Camiseta Puma Essentials Base",
            "Bermuda Adidas 3S Masculina",
            "Boné Nike Sportswear",
            "Boné Nike Aba Curva"
    );

    public static Product createRandomProduct() {
        int randomIndex = (int) (Math.random() * PRODUCT_NAME_LIST.size());

        return Product
                .builder()
                .productId(UUID.randomUUID())
                .name(PRODUCT_NAME_LIST.get(randomIndex))
                .build();
    }

    public static Product createProduct(String name) {
        return Product
                .builder()
                .productId(UUID.randomUUID())
                .name(name)
                .build();
    }

    public static List<Product> getMaximumProductList() {
        return PRODUCT_NAME_LIST
                .stream()
                .map(ProductFactory::createProduct)
                .toList();
    }
}

package com.ecommerce.wishlist.core.contracts;

public interface ModelMapper<Entity, Schema> {
    Entity mapToEntity(Schema schema);
    Schema mapToSchema(Entity domain);
}

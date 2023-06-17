package com.ecommerce.wishlist.core.contract;

public interface ModelMapper<Entity, Schema> {
    Entity mapToEntity(Schema schema);
    Schema mapToSchema(Entity domain);
}

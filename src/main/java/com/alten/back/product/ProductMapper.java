package com.alten.back.product;

import java.util.function.Function;

public class ProductMapper implements Function<ProductDTO,Product> {

    @Override
    public Product apply(ProductDTO  product) {
        return Product.builder()
                .id( product.getId() )
                .code( product.getCode() )
                .name( product.getName() )
                .description( product.getDescription() )
                .image( product.getImage() )
                .category( product.getCategory() )
                .price( product.getPrice() )
                .quantity( product.getQuantity() )
                .internalReference( product.getInternalReference() )
                .shellId( product.getShellId() )
                .inventoryStatus( product.getInventoryStatus() )
                .rating( product.getRating() )
                .createdAt( product.getCreatedAt() )
                .updatedAt( product.getUpdatedAt() )
                .build();
    }
}


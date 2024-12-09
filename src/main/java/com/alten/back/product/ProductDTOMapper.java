package com.alten.back.product;

import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ProductDTOMapper implements Function<Product, ProductDTO> {

    @Override
    public ProductDTO apply(Product product) {
        return ProductDTO.builder()
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

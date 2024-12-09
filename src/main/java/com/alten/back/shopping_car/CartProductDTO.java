package com.alten.back.shopping_car;

import com.alten.back.product.Product;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CartProductDTO {
    Product product;

    int quantity;
}

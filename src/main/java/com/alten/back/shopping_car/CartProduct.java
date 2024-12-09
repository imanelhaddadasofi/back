package com.alten.back.shopping_car;

import com.alten.back.product.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@AllArgsConstructor
@Document(value = "cart-product")
public class CartProduct {

    public CartProduct(Product product,int quantity) {
        this.quantity = quantity;
        this.product = product;
    }

    Product product;

    int quantity;
}

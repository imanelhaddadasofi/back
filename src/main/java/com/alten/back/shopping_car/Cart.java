package com.alten.back.shopping_car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@Document(value = "cart")
public class Cart implements Serializable {

    public Cart(String email,List<CartProduct> cartProducts) {
        this.cartProducts = cartProducts;
        this.email = email;
    }

    @Transient
    public static final String SEQUENCE_NAME = "cart_sequence";

    @Id
    private BigInteger id;

    private String email;

    List<CartProduct> cartProducts;

}

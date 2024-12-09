package com.alten.back.shopping_car;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CartDTO {

    private BigInteger id;

    private String email;

    List<CartProduct> cartProducts;
}

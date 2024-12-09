package com.alten.back.shopping_car;

import java.math.BigInteger;

public interface ShoppingCartService {

    CartDTO getCartByEmail(String email);

    CartDTO findById(BigInteger id);

    void addInCart(CartDTO cartDTO);

    void deleteFromCart(BigInteger id);

    CartDTO updateCart(BigInteger id,CartDTO cartDTO);

}

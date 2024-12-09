package com.alten.back.shopping_car;

import com.alten.back.product.Product;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Objects;
import java.util.Optional;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService{
    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;

    public ShoppingCartServiceImpl(CartRepository cartRepository,CartProductRepository cartProductRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository=cartProductRepository;
    }

    @Override
    public CartDTO getCartByEmail(String email) {
        return cartRepository.findCartByEmail(email).stream().map( c -> {return new CartDTO( c.getId(),c.getEmail(), c.getCartProducts()); }).findFirst().orElse(null);
    }

    @Override
    public CartDTO findById(BigInteger id) {
        return cartRepository.findById( id ).map( c-> {return new CartDTO( c.getId(),c.getEmail(), c.getCartProducts()); }).orElse( null );
    }

    @Override
    public void addInCart(CartDTO cartDTO) {
        CartDTO dto=getCartByEmail(cartDTO.getEmail());
        if(Objects.isNull( dto )){
            Cart c=new Cart( cartDTO.getEmail(),cartDTO.getCartProducts() );
            cartRepository.save( c );
            cartDTO.setId( c.getId() );
        }else{
            Optional<CartProduct> productExistInCart =dto.getCartProducts().stream().filter( cartProduct -> {
                return cartProduct.product.getId().equals( cartDTO.getCartProducts().get( 0 ).getProduct().getId() );
            } ).findFirst();
            productExistInCart.ifPresentOrElse(
                    cartProduct -> { cartProduct.setQuantity( cartProduct.getQuantity()+1);
                        cartProductRepository.save(cartProduct);
                        },
                    () -> {
                        CartProduct cartProduct=new CartProduct(dto.getCartProducts().get( 0 ).getProduct(), dto.getCartProducts().get( 0 ).getQuantity() );
                        cartProductRepository.save(cartProduct);
                    });
        }

    }

    @Override
    public void deleteFromCart(BigInteger id) {

    }

    @Override
    public CartDTO updateCart(BigInteger id, CartDTO cartDTO) {
        return null;
    }
}

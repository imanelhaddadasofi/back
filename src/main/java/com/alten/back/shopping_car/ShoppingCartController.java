package com.alten.back.shopping_car;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@Slf4j
@RequestMapping(path = "/api/cart")
@RestController
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("{email}")
    public ResponseEntity<List<CartDTO>> getCartByEmail(@PathVariable("email") String email) {
        List<CartDTO> payloads = shoppingCartService.getCartByEmail( email );
        return ResponseEntity.ok(payloads);
    }

    @GetMapping("{id}")
    public ResponseEntity<CartDTO> getProduct(@PathVariable("id") BigInteger id) {
        return ResponseEntity.ok(shoppingCartService.findById( id ));
    }

    @PostMapping
    public ResponseEntity<CartDTO> addInCart(@RequestBody CartDTO  request) {
        shoppingCartService.addInCart(request);
        return ResponseEntity.ok( request );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteFromCart(@PathVariable("id") BigInteger id) {
        shoppingCartService.deleteFromCart(id);
        return ResponseEntity.ok("Product deleted ");
    }

    @PatchMapping("{id}")
    public ResponseEntity<CartDTO> updateCustomer(@PathVariable("id") BigInteger id,@RequestBody CartDTO cartDTO) {
        shoppingCartService.updateCart( id, cartDTO );
        return ResponseEntity.ok( cartDTO );
    }



}

package com.alten.back.product;

import com.alten.back.security.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import java.math.BigInteger;
import java.util.List;

@Slf4j
@RequestMapping(path = "/api/products")
@RestController
public class ProductController {

    public static final String INSUFFICIENT_AUTHENTICATION_MSG = "Vous n'avez pas les droits nécessaires! ";
    private final ProductService productService;
    private final SecurityService securityService;

    public ProductController(ProductService productService,SecurityService securityService) {
        this.productService = productService;
        this.securityService=securityService;
    }


    @GetMapping()
    public ResponseEntity<List<ProductDTO>> getProducts() {
        List<ProductDTO> payloads = productService.getAllProducts();
        return ResponseEntity.ok(payloads);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("id") BigInteger id) {
        return ResponseEntity.ok(productService.findById( id ));
    }

    @PostMapping
    public ResponseEntity<ProductDTO> addProduct(@RequestBody ProductDTO request) {
        if(securityService.isNotAdmin())
            throw new InsufficientAuthenticationException( INSUFFICIENT_AUTHENTICATION_MSG );
        productService.addProduct(request);
        return ResponseEntity.ok( request );

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") BigInteger id) {
        if(securityService.isNotAdmin())
            throw new InsufficientAuthenticationException( INSUFFICIENT_AUTHENTICATION_MSG );
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted ");
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProductDTO> updateCustomer(@PathVariable("id") BigInteger id,@RequestBody ProductDTO product) {
        if(securityService.isNotAdmin())
            throw new InsufficientAuthenticationException( INSUFFICIENT_AUTHENTICATION_MSG );
        productService.updateProduct( id, product );
        return ResponseEntity.ok( product );
    }
}

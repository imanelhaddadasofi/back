package com.alten.back.product;

import java.math.BigInteger;
import java.util.List;

public interface ProductService {

    List<ProductDTO> getAllProducts();

    ProductDTO findById(BigInteger id);

    void addProduct(ProductDTO productDTO);

    void deleteProduct(BigInteger id);

    ProductDTO updateProduct(BigInteger id,ProductDTO productDTO);

}

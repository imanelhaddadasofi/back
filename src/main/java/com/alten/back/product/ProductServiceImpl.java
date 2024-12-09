package com.alten.back.product;

import com.alten.back.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductDTOMapper mapper;

    public ProductServiceImpl(ProductRepository repository, ProductDTOMapper mapper) {
        this.repository = repository;
        this.mapper=mapper;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return repository.findAll().stream().map( mapper ).toList();
    }

    @Override
    public ProductDTO findById(BigInteger id) {
        return repository.findById(id).map( mapper ).orElseThrow(() -> new ResourceNotFoundException(
                "product with id [%s] not found".formatted(id)
        ));
    }

    @Override
    public void addProduct(ProductDTO product) {
        product.setCreatedAt( LocalDateTime.now() );
        Product p=Product.builder()
                .id(product.getId())
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
         repository.save( p );
        product.setId( p.getId() );
    }

    @Override
    public void deleteProduct(BigInteger id) {

        ProductDTO product=repository.findById(id).map( mapper ).orElseThrow(() -> new ResourceNotFoundException(
                "product with id [%s] not found".formatted(id)
        ));
         Product p=Product.builder()
                .id(product.getId())
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
        repository.delete( p);
    }

    @Override
    public ProductDTO updateProduct(BigInteger id, ProductDTO product) {
        Product p=repository.findById(id).orElseThrow(() -> new ResourceNotFoundException(
                "product with id [%s] not found".formatted(id)
        ));
            p.setCode( product.getCode() );
            p.setName( product.getName() );
            p.setDescription( product.getDescription() );
            p.setImage( product.getImage() );
            p.setCategory( product.getCategory() );
            p.setPrice( product.getPrice() );
            p.setQuantity( product.getQuantity() );
            p.setInternalReference( product.getInternalReference() );
            p.setShellId( product.getShellId() );
            p.setInventoryStatus( product.getInventoryStatus() );
            p.setRating( product.getRating() );
            p.setUpdatedAt( LocalDateTime.now() );
        repository.save( p );
        product.setId(id);
        product.setUpdatedAt( p.getUpdatedAt() );
        product.setCreatedAt( p.getCreatedAt() );
        return product;
    }
}

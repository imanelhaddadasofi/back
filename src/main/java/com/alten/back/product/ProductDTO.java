package com.alten.back.product;

import lombok.Builder;
import lombok.Data;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductDTO {

    private BigInteger id;
    private String code;
    private String name;
    private String description;
    private String image;
    private String category;
    private double price;
    private int quantity;
    private String internalReference;
    private int shellId ;
    private Status inventoryStatus;
    private double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

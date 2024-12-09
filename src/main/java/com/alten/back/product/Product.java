package com.alten.back.product;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Transient;
import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@Document(value = "product")
public class Product implements Serializable {
    public Product(BigInteger id) {
        this.id = id;
    }

    @Transient
    public static final String SEQUENCE_NAME = "product_sequence";

    @Id
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

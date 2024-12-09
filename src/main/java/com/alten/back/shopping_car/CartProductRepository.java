package com.alten.back.shopping_car;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface CartProductRepository extends MongoRepository<CartProduct, BigInteger> {
}

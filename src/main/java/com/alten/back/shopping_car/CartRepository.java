package com.alten.back.shopping_car;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface CartRepository extends MongoRepository<Cart, BigInteger> {

    @Query(value = "{email : ?0}")
    List<Cart> findCartByEmail(String email);
}

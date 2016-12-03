package com.isometric.repository;

import com.isometric.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface OrderRepository extends MongoRepository<Order, BigInteger> {
    List<Order> findByUserIdAndIsCheckedOutIsTrue(BigInteger userId);
    List<Order> findByUserIdAndIsCheckedOutIsFalse(BigInteger userId);
}

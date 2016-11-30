package com.isometric.repository;

import com.isometric.entity.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface BidRepository extends MongoRepository<Bid, BigInteger> {
}

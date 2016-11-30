package com.isometric.repository;

import com.isometric.entity.Bid;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface BidRepository extends MongoRepository<Bid, BigInteger> {

    List<Bid> findByUserId(BigInteger userId);

    List<Bid> findByPostId(BigInteger postId);
}

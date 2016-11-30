package com.isometric.repository;

import com.isometric.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface PostRepository extends MongoRepository<Post, BigInteger> {
}

package com.isometric.repository;

import com.isometric.entity.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;
import java.util.List;

public interface PostRepository extends MongoRepository<Post, BigInteger> {
    List<Post> findByUserId(BigInteger userId);
    List<Post> findByPostTitleLike(String postTitle);
}

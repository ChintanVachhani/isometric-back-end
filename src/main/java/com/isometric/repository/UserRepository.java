package com.isometric.repository;

import com.isometric.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigInteger;

public interface UserRepository extends MongoRepository<User, BigInteger> {
    User findByUserName(String userName);
    User findByEmail(String email);
}

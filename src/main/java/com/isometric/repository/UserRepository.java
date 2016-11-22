package com.isometric.repository;

import com.isometric.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUserName(String userName);
    public User findByEmail(String email);
}

package com.isometric.repository;

import com.isometric.entity.ID;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IDRepository extends MongoRepository<ID, String> {
}

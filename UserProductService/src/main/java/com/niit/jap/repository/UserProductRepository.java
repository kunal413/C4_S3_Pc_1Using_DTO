package com.niit.jap.repository;

import com.niit.jap.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserProductRepository extends MongoRepository<User,Integer> {
User findByUserId(int userId);
}

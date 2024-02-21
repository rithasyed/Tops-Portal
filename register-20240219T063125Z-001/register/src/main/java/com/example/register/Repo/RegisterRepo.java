package com.example.register.Repo;
import com.example.register.Entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RegisterRepo extends MongoRepository<Register, String> {

}

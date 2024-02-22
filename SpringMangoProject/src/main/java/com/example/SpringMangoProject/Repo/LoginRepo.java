package com.example.SpringMangoProject.Repo;
import com.example.SpringMangoProject.Entity.*;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LoginRepo extends MongoRepository<Login, String> {

}

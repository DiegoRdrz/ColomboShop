package com.BackEnd.Colomboshop.Repository;

import com.BackEnd.Colomboshop.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
}
package com.BackEnd.Colomboshop.Repository;

import com.BackEnd.Colomboshop.Model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByCategoryID(String categoryID);

    @Query("{ 'nameProduct': { $regex: ?0, $options: 'i' } }")
    List<Product> findByNameProductRegex(String regex);

    List<Product> findByPriceBetween(double minPrice, double maxPrice);
}
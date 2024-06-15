package com.BackEnd.Colomboshop.Repository;

import com.BackEnd.Colomboshop.Model.ShoppingCart;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingCartRepository extends MongoRepository<ShoppingCart,String>{
}

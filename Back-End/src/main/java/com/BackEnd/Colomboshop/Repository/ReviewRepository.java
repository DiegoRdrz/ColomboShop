package com.BackEnd.Colomboshop.Repository;

import com.BackEnd.Colomboshop.Model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
}

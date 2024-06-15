package com.BackEnd.Colomboshop.Service;

import com.BackEnd.Colomboshop.Model.Review;
import com.BackEnd.Colomboshop.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Optional<Review> getReviewById(String id) {
        return reviewRepository.findById(id);
    }

    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    public Review updateReview(String id, Review review) {
        if (reviewRepository.existsById(id)) {
            review.setReviewID(id);
            return reviewRepository.save(review);
        } else {
            throw new RuntimeException("Review not found");
        }
    }

    public void deleteReview(String id) {
        reviewRepository.deleteById(id);
    }
}

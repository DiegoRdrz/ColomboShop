package com.BackEnd.Colomboshop.Controller;

import com.BackEnd.Colomboshop.DTO.ReviewDTO;
import com.BackEnd.Colomboshop.Model.Review;
import com.BackEnd.Colomboshop.Service.ReviewService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/colomboShop/user/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return reviews.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDTO> getReviewById(@PathVariable String id) {
        Optional<Review> review = reviewService.getReviewById(id);
        return review.map(value -> ResponseEntity.ok(convertToDto(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewDTO reviewDTO) throws Exception {
        Review review = convertToEntity(reviewDTO);
        Review createdReview = reviewService.createReview (review);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(createdReview));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewDTO> updateReview(@PathVariable String id, @RequestBody ReviewDTO reviewDTO) {
        Review review = convertToEntity(reviewDTO);
        Review updatedReview = reviewService.updateReview(id, review);
        return ResponseEntity.ok(convertToDto(updatedReview));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    private ReviewDTO convertToDto(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO();
        BeanUtils.copyProperties(review, reviewDTO);
        return reviewDTO;
    }

    private Review convertToEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        BeanUtils.copyProperties(reviewDTO, review);
        return review;
    }
}


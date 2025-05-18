package com.yourteam.pgno54_hometutorbookingsystems.service;

import com.yourteam.pgno54_hometutorbookingsystems.model.PublicReview;
import com.yourteam.pgno54_hometutorbookingsystems.model.Review;
import com.yourteam.pgno54_hometutorbookingsystems.model.VerifiedReview;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReviewService {
    private static final String FILE_PATH = "reviews.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void createReview(Review review) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            String line = String.format("%d,%s,%s,%d,%s,%s,%s",
                    review.getReviewId(),
                    review.getTutorName(),
                    review.getStudentName(),
                    review.getRating(),
                    review.getComment(),
                    review.getCreatedAt().format(FORMATTER),
                    review instanceof VerifiedReview ? ((VerifiedReview) review).isVerified() : false);
            writer.write(line);
            writer.newLine();
        }
    }

    public List<Review> readReviews() throws IOException {
        List<Review> reviews = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return reviews;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 7) continue;

                int reviewId = Integer.parseInt(parts[0]);
                String tutorName = parts[1];
                String studentName = parts[2];
                int rating = Integer.parseInt(parts[3]);
                String comment = parts[4];
                LocalDateTime createdAt = LocalDateTime.parse(parts[5], FORMATTER);
                boolean verified = Boolean.parseBoolean(parts[6]);

                Review review = verified ?
                        new VerifiedReview(reviewId, tutorName, studentName, rating, comment, verified) :
                        new PublicReview(reviewId, tutorName, studentName, rating, comment);
                reviews.add(review);
            }
        }
        return reviews;
    }

    public void updateReview(int reviewId, int rating, String comment) throws IOException {
        List<Review> reviews = readReviews();
        File file = new File(FILE_PATH);
        if (file.exists()) file.delete();

        for (Review review : reviews) {
            if (review.getReviewId() == reviewId) {
                review.setRating(rating);
                review.setComment(comment);
            }
            createReview(review);
        }
    }

    public void deleteReview(int reviewId) throws IOException {
        List<Review> reviews = readReviews();
        File file = new File(FILE_PATH);
        if (file.exists()) file.delete();

        for (Review review : reviews) {
            if (review.getReviewId() != reviewId) {
                createReview(review);
            }
        }
    }

    public int generateReviewId() {
        return new Random().nextInt(10000) + 1; // Simple ID generation
    }
}
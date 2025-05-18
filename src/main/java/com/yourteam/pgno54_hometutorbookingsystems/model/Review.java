package com.yourteam.pgno54_hometutorbookingsystems.model;

import java.time.LocalDateTime;

public abstract class Review {
    private int reviewId;
    private String tutorName;
    private String studentName;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;

    public Review(int reviewId, String tutorName, String studentName, int rating, String comment) {
        this.reviewId = reviewId;
        this.tutorName = tutorName;
        this.studentName = studentName;
        this.rating = rating;
        this.comment = comment;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public int getReviewId() { return reviewId; }
    public void setReviewId(int reviewId) { this.reviewId = reviewId; }
    public String getTutorName() { return tutorName; }
    public void setTutorName(String tutorName) { this.tutorName = tutorName; }
    public String getStudentName() { return studentName; }
    public void setStudentName(String studentName) { this.studentName = studentName; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    // Abstract method for polymorphic display
    public abstract String displayReview();
}
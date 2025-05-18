package com.yourteam.pgno54_hometutorbookingsystems.model;

public class PublicReview extends Review {
    public PublicReview(int reviewId, String tutorName, String studentName, int rating, String comment) {
        super(reviewId, tutorName, studentName, rating, comment);
    }

    @Override
    public String displayReview() {
        return "Public Review [ID: " + getReviewId() + ", Tutor: " + getTutorName() +
                ", Student: " + getStudentName() + ", Rating: " + getRating() +
                ", Comment: " + getComment() + "]";
    }
}
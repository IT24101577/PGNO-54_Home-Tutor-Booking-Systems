package com.yourteam.pgno54_hometutorbookingsystems.model;

public class VerifiedReview extends Review {
    private boolean verified;

    public VerifiedReview(int reviewId, String tutorName, String studentName, int rating, String comment, boolean verified) {
        super(reviewId, tutorName, studentName, rating, comment);
        this.verified = verified;
    }

    public boolean isVerified() { return verified; }
    public void setVerified(boolean verified) { this.verified = verified; }

    @Override
    public String displayReview() {
        return "Verified Review [ID: " + getReviewId() + ", Tutor: " + getTutorName() +
                ", Student: " + getStudentName() + ", Rating: " + getRating() +
                ", Comment: " + getComment() + ", Verified: " + verified + "]";
    }
}
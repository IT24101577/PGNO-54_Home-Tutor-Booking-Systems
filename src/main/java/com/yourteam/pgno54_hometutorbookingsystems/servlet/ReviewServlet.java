package com.yourteam.pgno54_hometutorbookingsystems.servlet;

import com.yourteam.pgno54_hometutorbookingsystems.model.PublicReview;
import com.yourteam.pgno54_hometutorbookingsystems.model.Review;
import com.yourteam.pgno54_hometutorbookingsystems.service.ReviewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/submitReview")
public class ReviewServlet extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init() {
        reviewService = new ReviewService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tutorName = request.getParameter("tutorName");
        String studentName = request.getParameter("studentName");
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        int reviewId = reviewService.generateReviewId();
        Review review = new PublicReview(reviewId, tutorName, studentName, rating, comment);
        reviewService.createReview(review);

        response.sendRedirect("review.jsp?success=true");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int reviewId = Integer.parseInt(request.getParameter("reviewId"));
        int rating = Integer.parseInt(request.getParameter("rating"));
        String comment = request.getParameter("comment");

        if (request.getParameter("action").equals("update")) {
            reviewService.updateReview(reviewId, rating, comment);
            response.sendRedirect("viewReviews.jsp?updated=true");
        } else if (request.getParameter("action").equals("delete")) {
            reviewService.deleteReview(reviewId);
            response.sendRedirect("viewReviews.jsp?deleted=true");
        }
    }
}
package com.yourteam.pgno54_hometutorbookingsystems.servlet;

import com.yourteam.pgno54_hometutorbookingsystems.model.Review;
import com.yourteam.pgno54_hometutorbookingsystems.service.ReviewService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewReviews")
public class ViewReviewsServlet extends HttpServlet {
    private ReviewService reviewService;

    @Override
    public void init() {
        reviewService = new ReviewService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Review> reviews = reviewService.readReviews();
        request.setAttribute("reviews", reviews);
        request.getRequestDispatcher("viewReviews.jsp").forward(request, response);
    }
}
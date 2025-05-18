package com.yourteam.hometutorsearchbookingsystem.servlet;

import com.yourteam.hometutorsearchbookingsystem.model.Tutor;
import com.yourteam.hometutorsearchbookingsystem.service.ITutorService;
import com.yourteam.hometutorsearchbookingsystem.service.TutorManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/TutorSearchSortServlet")
public class TutorSearchSortServlet extends HttpServlet {
    private ITutorService tutorService;

    @Override
    public void init() throws ServletException {
        try {
            tutorService = new TutorManager();
        } catch (IOException e) {
            throw new ServletException("Failed to initialize TutorService", e);
        }
    }

    //

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String subject = request.getParameter("subject");
        String name = request.getParameter("name");
        String minRatingStr = request.getParameter("minRating");
        String sortCriterion = request.getParameter("sortCriterion");
        double minRating = minRatingStr != null && !minRatingStr.isEmpty() ? Double.parseDouble(minRatingStr) : 0.0;

        List<Tutor> tutors = tutorService.searchAndSortTutors(subject, name, minRating, sortCriterion != null ? sortCriterion : "rating");
        request.setAttribute("tutors", tutors);
        request.getRequestDispatcher("/tutorSearchSort.jsp").forward(request, response);
    }
}
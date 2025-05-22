package com.yourteam.pgno54_hometutorbookingsystems.servlet;

import com.yourteam.pgno54_hometutorbookingsystems.model.Tutor;
import com.yourteam.pgno54_hometutorbookingsystems.service.ITutorService;
import com.yourteam.pgno54_hometutorbookingsystems.service.TutorManager;
import com.yourteam.pgno54_hometutorbookingsystems.util.CustomList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/TutorServlet")
public class TutorServlet extends HttpServlet {
    private ITutorService tutorService; // Polymorphism: uses interface

    @Override
    public void init() throws ServletException {
        try {
            tutorService = new TutorManager(); // Can swap with other implementations
            System.out.println("TutorService initialized successfully");
        } catch (Exception e) {
            throw new ServletException("Failed to initialize TutorService", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("profile".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.trim().isEmpty()) {
                Tutor tutor = Arrays.stream(tutorService.getAllTutors())
                        .filter(t -> t.getId().equals(id))
                        .findFirst()
                        .orElse(null);
                request.setAttribute("tutor", tutor);
                request.getRequestDispatcher("/tutorProfile.jsp").forward(request, response);
            } else {
                response.sendRedirect("TutorServlet");
            }
        } else if ("search".equals(action)) {
            String subject = request.getParameter("subject");
            String name = request.getParameter("name");
            String minRatingStr = request.getParameter("minRating");
            double minRating = minRatingStr != null && !minRatingStr.isEmpty() ? Double.parseDouble(minRatingStr) : 0.0;
            System.out.println("Search requested - subject: " + subject + ", name: " + name + ", minRating: " + minRating);
            List<Tutor> tutors = tutorService.searchTutors(
                    subject != null ? subject.trim().toLowerCase() : "",
                    name != null ? name.trim().toLowerCase() : "",
                    minRating
            );
            System.out.println("Found " + tutors.size() + " tutors");
            request.setAttribute("tutors", tutors);
            request.getRequestDispatcher("/tutorSearch.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/tutorRegistration.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("doPost action: " + action + ", id: " + request.getParameter("id"));

        if ("add".equals(action)) {
            try {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String subject = request.getParameter("subject").toLowerCase();
                double rating = Double.parseDouble(request.getParameter("rating"));
                Tutor tutor = new Tutor(id, name, subject, rating);
                tutorService.addTutor(tutor);
                response.sendRedirect("TutorServlet?action=profile&id=" + id);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid rating value. Please enter a number.");
                request.getRequestDispatcher("/tutorRegistration.jsp").forward(request, response);
            } catch (IOException e) {
                request.setAttribute("error", e.getMessage());
                request.getRequestDispatcher("/tutorRegistration.jsp").forward(request, response);
            }
        } else if ("update".equals(action)) {
            try {
                String id = request.getParameter("id");
                String name = request.getParameter("name");
                String subject = request.getParameter("subject").toLowerCase();
                double rating = Double.parseDouble(request.getParameter("rating"));
                Tutor tutor = new Tutor(id, name, subject, rating);
                tutorService.updateTutor(tutor);
                response.sendRedirect("TutorServlet?action=profile&id=" + id);
            } catch (NumberFormatException e) {
                request.setAttribute("error", "Invalid rating value.");
                request.getRequestDispatcher("/tutorProfile.jsp").forward(request, response);
            } catch (IOException e) {
                throw new ServletException("Error updating tutor", e);
            }
        } else if ("delete".equals(action)) {
            try {
                String id = request.getParameter("id");
                System.out.println("Servlet initiating delete for ID: " + id);
                tutorService.deleteTutor(id);
                System.out.println("Servlet completed delete for ID: " + id);
                response.sendRedirect("TutorServlet");
            } catch (IOException e) {
                throw new ServletException("Error deleting tutor", e);
            }
        } else {
            response.sendRedirect("TutorServlet");
        }
    }
}
package com.yourteam.pgno54_hometutorbookingsystems.servlet;

import com.yourteam.pgno54_hometutorbookingsystems.model.Student;
import com.yourteam.pgno54_hometutorbookingsystems.service.StudentManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/StudentSearchServlet")
public class StudentSearchServlet extends HttpServlet {
    private StudentManager studentManager;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("Initializing StudentManager for StudentSearchServlet...");
            studentManager = new StudentManager();
        } catch (IOException e) {
            System.err.println("Failed to initialize StudentManager");
            e.printStackTrace();
            throw new ServletException("Failed to initialize StudentManager", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        System.out.println("Handling GET request for StudentSearchServlet with searchQuery: " + searchQuery);

        List<Student> students;
        if (searchQuery == null || searchQuery.trim().isEmpty()) {
            students = studentManager.getAllStudents();
            System.out.println("No search query provided, retrieving all students");
        } else {
            System.out.println("Searching students with query: " + searchQuery);
            students = studentManager.getAllStudents().stream()
                    .filter(s -> s.getId().toLowerCase().contains(searchQuery.toLowerCase()) ||
                            s.getName().toLowerCase().contains(searchQuery.toLowerCase()))
                    .collect(Collectors.toList());
        }

        request.setAttribute("students", students);
        request.getRequestDispatcher("/studentSearch.jsp").forward(request, response);
    }
}

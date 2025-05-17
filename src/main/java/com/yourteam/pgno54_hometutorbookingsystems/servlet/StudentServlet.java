package com.yourteam.pgno54_hometutorbookingsystems.servlet;

import com.yourteam.pgno54_hometutorbookingsystems.model.Student;
import com.yourteam.pgno54_hometutorbookingsystems.service.StudentManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
    private StudentManager studentManager;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("Initializing StudentManager...");
            studentManager = new StudentManager();
        } catch (IOException e) {
            System.err.println("Failed to initialize StudentManager");
            e.printStackTrace();
            throw new ServletException("Failed to initialize StudentManager", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Handling GET request with action: " + action);

        if ("profile".equals(action)) {
            String id = request.getParameter("id");
            if (id != null && !id.trim().isEmpty()) {
                System.out.println("Fetching profile for student ID: " + id);
                Student student = studentManager.getStudentById(id);
                request.setAttribute("student", student);
                request.getRequestDispatcher("/studentProfile.jsp").forward(request, response);
            } else {
                System.out.println("Invalid or missing student ID, redirecting to login");
                response.sendRedirect("StudentServlet?action=login");
            }
        } else if ("login".equals(action)) {
            System.out.println("Forwarding to student login page");
            request.getRequestDispatcher("/studentLogin.jsp").forward(request, response);
        } else {
            System.out.println("No action specified, forwarding to student login page");
            request.getRequestDispatcher("/studentLogin.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        System.out.println("Handling POST request with action: " + action + ", id: " + id);

        if ("login".equals(action)) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                System.out.println("Attempting to login with username: " + username);
                Student student = studentManager.authenticateStudent(username, password);
                if (student != null) {
                    System.out.println("Login successful for student ID: " + student.getId());
                    request.setAttribute("studentId", student.getId());
                    request.getRequestDispatcher("/studentWelcome.jsp").forward(request, response);
                } else {
                    System.out.println("Login failed for username: " + username);
                    request.setAttribute("error", "Invalid username or password.");
                    request.getRequestDispatcher("/studentLogin.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.err.println("Error during student login for username: " + request.getParameter("username"));
                e.printStackTrace();
                request.setAttribute("error", "Error during login: " + e.getMessage());
                request.getRequestDispatcher("/studentLogin.jsp").forward(request, response);
            }
        } else if ("add".equals(action)) {
            try {
                String name = request.getParameter("name");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                List<String> preferredSubjects = Arrays.asList(request.getParameter("preferredSubjects").split(","));
                String contactDetails = request.getParameter("contactDetails");
                if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                    throw new IllegalArgumentException("Username and password are required.");
                }
                if (username.contains(" ")) {
                    request.setAttribute("error", "Username must not contain spaces.");
                    request.getRequestDispatcher("/studentRegistration.jsp").forward(request, response);
                    return;
                }
                Student student = new Student(id, name, username, password, preferredSubjects, contactDetails);
                System.out.println("Adding new student: " + student.getId());
                studentManager.addStudent(student);
                response.sendRedirect("StudentServlet?action=profile&id=" + id);
            } catch (Exception e) {
                System.err.println("Error adding student ID: " + id);
                e.printStackTrace();
                request.setAttribute("error", "Error adding student: " + e.getMessage());
                request.getRequestDispatcher("/studentRegistration.jsp").forward(request, response);
            }
        } else if ("update".equals(action)) {
            try {
                String name = request.getParameter("name");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                List<String> preferredSubjects = Arrays.asList(request.getParameter("preferredSubjects").split(","));
                String contactDetails = request.getParameter("contactDetails");
                if (username == null || username.trim().isEmpty() || password == null || password.trim().isEmpty()) {
                    throw new IllegalArgumentException("Username and password are required.");
                }
                Student student = new Student(id, name, username, password, preferredSubjects, contactDetails);
                System.out.println("Updating student: " + student.getId());
                studentManager.updateStudent(id, student);
                response.sendRedirect("StudentServlet?action=profile&id=" + id);
            } catch (Exception e) {
                System.err.println("Error updating student ID: " + id);
                e.printStackTrace();
                request.setAttribute("error", "Error updating student: " + e.getMessage());
                request.getRequestDispatcher("/studentProfile.jsp").forward(request, response);
            }
        } else if ("delete".equals(action)) {
            try {
                System.out.println("Attempting to delete student: " + id);
                Student studentBefore = studentManager.getStudentById(id);
                if (studentBefore != null) {
                    studentManager.deleteStudent(id);
                    System.out.println("Student deleted successfully: " + id);
                    String role = request.getParameter("role") != null ? request.getParameter("role") : "student";
                    if ("admin".equalsIgnoreCase(role)) {
                        response.sendRedirect("adminDeletionSuccess.jsp");
                    } else {
                        response.sendRedirect("deletionSuccess.jsp?role=student");
                    }
                } else {
                    System.out.println("Student not found for deletion: " + id);
                    request.setAttribute("error", "Student not found for deletion.");
                    request.getRequestDispatcher("/studentProfile.jsp").forward(request, response);
                }
            } catch (IOException e) {
                System.err.println("Error deleting student ID: " + id + ": " + e.getMessage());
                e.printStackTrace();
                request.setAttribute("error", "Failed to delete student: " + e.getMessage());
                Student student = studentManager.getStudentById(id);
                request.setAttribute("student", student);
                request.getRequestDispatcher("/studentProfile.jsp").forward(request, response);
            }
        } else {
            System.out.println("Unknown action: " + action + ", redirecting to student login");
            response.sendRedirect("StudentServlet?action=login");
        }
    }
}

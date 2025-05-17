package com.yourteam.pgno54_hometutorbookingsystems.servlet;

import com.yourteam.pgno54_hometutorbookingsystems.service.AdminManager;
import com.yourteam.pgno54_hometutorbookingsystems.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private AdminManager adminManager;

    @Override
    public void init() throws ServletException {
        try {
            System.out.println("Initializing AdminManager...");
            adminManager = new AdminManager();
        } catch (IOException e) {
            System.err.println("Failed to initialize AdminManager");
            e.printStackTrace();
            throw new ServletException("Failed to initialize AdminManager", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Handling GET request for AdminServlet");
        request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        System.out.println("Handling POST request with action: " + action);

        if ("adminLogin".equals(action)) {
            try {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                System.out.println("Attempting to login admin with username: " + username);
                Admin admin = adminManager.authenticateAdmin(username, password);
                if (admin != null) {
                    System.out.println("Admin login successful for username: " + username);
                    response.sendRedirect("adminDashboard.jsp");
                } else {
                    System.out.println("Admin login failed for username: " + username);
                    request.setAttribute("error", "Invalid admin username or password.");
                    request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
                }
            } catch (Exception e) {
                System.err.println("Error during admin login for username: " + request.getParameter("username"));
                e.printStackTrace();
                request.setAttribute("error", "Error during admin login: " + e.getMessage());
                request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
            }
        } else {
            System.out.println("Unknown action: " + action + ", redirecting to admin login");
            response.sendRedirect("AdminServlet");
        }
    }
}

package com.hometutor.component04.booking.servlet;

import com.hometutor.component04.booking.manager.BookingFileManager;
import com.hometutor.component04.booking.model.Booking;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/viewBookings")
public class ViewBookingsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String studentId = request.getParameter("studentId") != null ? request.getParameter("studentId") : "student1";

            // Retrieve bookings for the student
            String filePath = "C:\\Users\\Akila Perera\\Desktop\\OOP Project\\HomeTutor\\PGNO-54_Home-Tutor-Booking-Systems\\src\\main\\webapp\\WEB-INF\\data\\bookings.txt";
            BookingFileManager fileManager = new BookingFileManager(filePath);
            List<Booking> bookings = fileManager.readBookings(studentId);

            // Set bookings in request for JSP
            request.setAttribute("bookings", bookings);
            request.getRequestDispatcher("/component04/bookingHistory.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to load bookings: " + e.getMessage());
            request.getRequestDispatcher("/component04/bookingHistory.jsp").forward(request, response);
        }
    }
}
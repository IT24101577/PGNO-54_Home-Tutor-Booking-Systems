package com.hometutor.component04.booking.servlet;

import com.hometutor.component04.booking.manager.BookingFileManager;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteBooking")
public class DeleteBookingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract parameters
            String bookingId = request.getParameter("bookingId");
            String studentId = request.getParameter("studentId");
            if (bookingId == null || studentId == null) {
                throw new IllegalArgumentException("Booking ID or Student ID is missing");
            }

            // Delete booking from file
            String filePath = "C:\\Users\\Akila Perera\\Desktop\\OOP Project\\HomeTutor\\PGNO-54_Home-Tutor-Booking-Systems\\src\\main\\webapp\\WEB-INF\\data\\bookings.txt";
            System.out.println("Deleting booking with ID: " + bookingId + " from path: " + filePath);
            BookingFileManager fileManager = new BookingFileManager(filePath);
            fileManager.deleteBooking(bookingId);

            // Redirect to booking history
            response.sendRedirect("viewBookings?studentId=" + studentId);
        } catch (Exception e) {
            System.err.println("Error deleting booking: " + e.getMessage());
            request.setAttribute("error", "Failed to delete booking: " + e.getMessage());
            request.getRequestDispatcher("/component04/bookingHistory.jsp").forward(request, response);
        }
    }
}
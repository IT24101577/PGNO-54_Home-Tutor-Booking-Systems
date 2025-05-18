package com.hometutor.component04.booking.servlet;

import com.hometutor.component04.booking.manager.BookingFileManager;
import com.hometutor.component04.booking.model.Booking;
import com.hometutor.component04.booking.model.InPersonSession;
import com.hometutor.component04.booking.model.OnlineSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/updateBooking")
public class UpdateBookingServlet extends HttpServlet {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Extract form parameters
            String bookingId = request.getParameter("bookingId");
            String studentId = request.getParameter("studentId");
            String tutorId = request.getParameter("tutorId");
            String dateTimeStr = request.getParameter("dateTime");
            String status = request.getParameter("status");
            String sessionType = request.getParameter("sessionType");
            String meetingLink = request.getParameter("meetingLink");
            String location = request.getParameter("location");

            // Validate inputs
            if (bookingId == null || studentId == null || tutorId == null || dateTimeStr == null || status == null || sessionType == null) {
                throw new IllegalArgumentException("Required fields are missing");
            }

            // Parse date and time
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, FORMATTER);

            // Create updated Booking object
            Booking updatedBooking;
            if ("Online".equals(sessionType)) {
                if (meetingLink == null || meetingLink.trim().isEmpty()) {
                    throw new IllegalArgumentException("Meeting link is required for online sessions");
                }
                updatedBooking = new OnlineSession(bookingId, studentId, tutorId, dateTime, status, meetingLink);
            } else if ("InPerson".equals(sessionType)) {
                if (location == null || location.trim().isEmpty()) {
                    throw new IllegalArgumentException("Location is required for in-person sessions");
                }
                updatedBooking = new InPersonSession(bookingId, studentId, tutorId, dateTime, status, location);
            } else {
                throw new IllegalArgumentException("Invalid session type");
            }

            // Update booking in file
            String filePath = "C:\\Users\\Akila Perera\\Desktop\\OOP Project\\HomeTutor\\PGNO-54_Home-Tutor-Booking-Systems\\src\\main\\webapp\\WEB-INF\\data\\bookings.txt";
            System.out.println("Updating booking with ID: " + bookingId + " at path: " + filePath);
            BookingFileManager fileManager = new BookingFileManager(filePath);
            fileManager.updateBooking(bookingId, updatedBooking);

            // Redirect to booking history
            response.sendRedirect("viewBookings?studentId=" + studentId);
        } catch (Exception e) {
            System.err.println("Error updating booking: " + e.getMessage());
            request.setAttribute("error", "Failed to update booking: " + e.getMessage());
            request.getRequestDispatcher("/component04/bookingHistory.jsp").forward(request, response);
        }
    }
}
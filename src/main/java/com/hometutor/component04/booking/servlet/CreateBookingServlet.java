package com.hometutor.component04.booking.servlet;

import com.hometutor.component04.booking.manager.BookingFileManager;
import com.hometutor.component04.booking.model.Booking;
import com.hometutor.component04.booking.model.OnlineSession;
import com.hometutor.component04.booking.model.InPersonSession;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/createBooking")
public class CreateBookingServlet extends HttpServlet {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Extract form parameters
        String studentId = request.getParameter("studentId");
        String tutorId = request.getParameter("tutorId");
        String dateTimeStr = request.getParameter("dateTime");
        String sessionType = request.getParameter("sessionType");
        String meetingLink = request.getParameter("meetingLink");
        String location = request.getParameter("location");

        try {
            // Validate inputs
            if (studentId == null || tutorId == null || dateTimeStr == null || sessionType == null) {
                throw new IllegalArgumentException("Required fields are missing");
            }

            // Parse date and time
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, FORMATTER);

            // Generate unique booking ID
            String bookingId = BookingFileManager.generateBookingId();

            // Create Booking object based on session type
            Booking booking;
            if ("Online".equals(sessionType)) {
                if (meetingLink == null || meetingLink.trim().isEmpty()) {
                    throw new IllegalArgumentException("Meeting link is required for online sessions");
                }
                booking = new OnlineSession(bookingId, studentId, tutorId, dateTime, "Confirmed", meetingLink);
            } else if ("InPerson".equals(sessionType)) {
                if (location == null || location.trim().isEmpty()) {
                    throw new IllegalArgumentException("Location is required for in-person sessions");
                }
                booking = new InPersonSession(bookingId, studentId, tutorId, dateTime, "Confirmed", location);
            } else {
                throw new IllegalArgumentException("Invalid session type");
            }

            // Save booking to file
            String filePath = "C:\\Users\\Akila Perera\\Desktop\\OOP Project\\HomeTutor\\PGNO-54_Home-Tutor-Booking-Systems\\src\\main\\webapp\\WEB-INF\\data\\bookings.txt";
            System.out.println("Using file path: " + filePath);
            BookingFileManager fileManager = new BookingFileManager(filePath);
            fileManager.createBooking(booking);

            // Store booking in request for confirmation page
            request.setAttribute("booking", booking);
            request.getRequestDispatcher("/component04/bookingConfirmation.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("error", "Failed to create booking: " + e.getMessage());
            request.getRequestDispatcher("/component04/booking.jsp").forward(request, response);
        }
    }
}
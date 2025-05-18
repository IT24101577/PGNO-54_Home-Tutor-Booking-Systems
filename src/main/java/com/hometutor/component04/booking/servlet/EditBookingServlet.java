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

@WebServlet("/editBooking")
public class EditBookingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String bookingId = request.getParameter("bookingId");
            if (bookingId == null) {
                throw new IllegalArgumentException("Booking ID is missing");
            }

            String filePath = "C:\\Users\\Akila Perera\\Desktop\\OOP Project\\HomeTutor\\PGNO-54_Home-Tutor-Booking-Systems\\src\\main\\webapp\\WEB-INF\\data\\bookings.txt";
            System.out.println("Loading booking with ID: " + bookingId + " from path: " + filePath);
            BookingFileManager fileManager = new BookingFileManager(filePath);
            List<Booking> bookings = fileManager.readBookings(null);

            Booking booking = bookings.stream()
                    .filter(b -> b.getBookingId().equals(bookingId))
                    .findFirst()
                    .orElse(null);

            if (booking == null) {
                throw new IllegalArgumentException("Booking not found");
            }

            request.setAttribute("booking", booking);
            request.getRequestDispatcher("/component04/editBooking.jsp").forward(request, response);
        } catch (Exception e) {
            System.err.println("Error loading booking: " + e.getMessage());
            request.setAttribute("error", "Failed to load booking: " + e.getMessage());
            request.getRequestDispatcher("/component04/bookingHistory.jsp").forward(request, response);
        }
    }
}
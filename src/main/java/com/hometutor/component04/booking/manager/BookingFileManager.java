package com.hometutor.component04.booking.manager;
import com.hometutor.component04.booking.model.Booking;
import com.hometutor.component04.booking.model.OnlineSession;
import com.hometutor.component04.booking.model.InPersonSession;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookingFileManager {
    private final String filePath;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public BookingFileManager(String filePath) {
        this.filePath = filePath;
    }

    public BookingFileManager() {
        this.filePath = "data/bookings.txt";
    }

    // Create a new booking
    public void createBooking(Booking booking) throws IOException {
        File file = new File(filePath);
        File parentDir = file.getParentFile();
        System.out.println("Attempting to create/write to file: " + file.getAbsolutePath());
        if (parentDir != null && !parentDir.exists()) {
            System.out.println("Creating directory: " + parentDir.getAbsolutePath());
            if (!parentDir.mkdirs()) {
                throw new IOException("Failed to create directory: " + parentDir.getAbsolutePath());
            }
        }
        if (!file.exists()) {
            System.out.println("Creating file: " + file.getAbsolutePath());
            if (!file.createNewFile()) {
                throw new IOException("Failed to create file: " + file.getAbsolutePath());
            }
        }
        System.out.println("File exists after creation: " + file.exists());
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(booking.toFileString());
            writer.newLine();
            System.out.println("Successfully wrote booking: " + booking.toFileString());
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            throw e;
        }
    }

    // Load all bookings
    public List<Booking> readBookings(String studentId) throws IOException {
        List<Booking> bookings = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("File does not exist, creating: " + file.getAbsolutePath());
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Booking booking = parseBooking(line);
                if (booking != null && (studentId == null || booking.getStudentId().equals(studentId))) {
                    bookings.add(booking);
                }
            }
        }
        return bookings;
    }

    // updated booking
    public void updateBooking(String bookingId, Booking updatedBooking) throws IOException {
        List<Booking> bookings = readBookings(null);
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Booking booking : bookings) {
                if (booking.getBookingId().equals(bookingId)) {
                    writer.write(updatedBooking.toFileString());
                } else {
                    writer.write(booking.toFileString());
                }
                writer.newLine();
            }
        }
    }

    // Delete specified booking
    public void deleteBooking(String bookingId) throws IOException {
        List<Booking> bookings = readBookings(null);
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Booking booking : bookings) {
                if (!booking.getBookingId().equals(bookingId)) {
                    writer.write(booking.toFileString());
                    writer.newLine();
                }
            }
        }
    }

    // Parse line from the file into Booking object
    private Booking parseBooking(String line) {
        String[] parts = line.split("\\|");
        if (parts.length < 6) return null;

        String bookingId = parts[0];
        String studentId = parts[1];
        String tutorId = parts[2];
        LocalDateTime dateTime = LocalDateTime.parse(parts[3], FORMATTER);
        String status = parts[4];
        String type = parts[5];

        if (type.equals("Online") && parts.length == 7) {
            String meetingLink = parts[6];
            return new OnlineSession(bookingId, studentId, tutorId, dateTime, status, meetingLink);
        } else if (type.equals("InPerson") && parts.length == 7) {
            String location = parts[6];
            return new InPersonSession(bookingId, studentId, tutorId, dateTime, status, location);
        }
        return null;
    }

    //generate a unique booking ID
    public static String generateBookingId() {
        return UUID.randomUUID().toString();
    }


    public static void main(String[] args) {
        try {
            BookingFileManager manager = new BookingFileManager();
            Booking booking = new OnlineSession(
                    generateBookingId(),
                    "student1",
                    "tutor1",
                    LocalDateTime.now().plusDays(1),
                    "Confirmed",
                    "https://zoom.us/j/123456"
            );
            manager.createBooking(booking);
            List<Booking> bookings = manager.readBookings(null);
            for (Booking b : bookings) {
                System.out.println(b.toFileString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
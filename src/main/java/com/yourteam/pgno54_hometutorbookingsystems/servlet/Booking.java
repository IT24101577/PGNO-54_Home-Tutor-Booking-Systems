package com.yourteam.pgno54_hometutorbookingsystems.servlet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Booking {
    protected String bookingId;
    protected String studentId;
    protected String tutorId;
    protected LocalDateTime dateTime;
    protected String status;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public Booking(String bookingId, String studentId, String tutorId, LocalDateTime dateTime, String status) {
        this.bookingId = bookingId;
        this.studentId = studentId;
        this.tutorId = tutorId;
        this.dateTime = dateTime;
        this.status = status;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getTutorId() {
        return tutorId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getStatus() {
        return status;
    }

    public abstract String getBookingType();

    public String toFileString() {
        return String.format("%s|%s|%s|%s|%s|%s",
                bookingId, studentId, tutorId, dateTime.format(FORMATTER), status, getBookingType());
    }
}
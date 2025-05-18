package com.hometutor.component04.booking.model;

import java.time.LocalDateTime;

public class InPersonSession extends Booking {
    private String location;

    public InPersonSession(String bookingId, String studentId, String tutorId, LocalDateTime dateTime, String status, String location) {
        super(bookingId, studentId, tutorId, dateTime, status);
        this.location = location;
    }

    @Override
    public String getBookingType() {
        return "InPerson";
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "|" + location;
    }
}
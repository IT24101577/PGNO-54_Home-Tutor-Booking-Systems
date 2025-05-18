package com.hometutor.component04.booking.model;

import java.time.LocalDateTime;

public class OnlineSession extends Booking {
    private String meetingLink;

    public OnlineSession(String bookingId, String studentId, String tutorId, LocalDateTime dateTime, String status, String meetingLink) {
        super(bookingId, studentId, tutorId, dateTime, status);
        this.meetingLink = meetingLink;
    }

    @Override
    public String getBookingType() {
        return "Online";
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "|" + meetingLink;
    }
}

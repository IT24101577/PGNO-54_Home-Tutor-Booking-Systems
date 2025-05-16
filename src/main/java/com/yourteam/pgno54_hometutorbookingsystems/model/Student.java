package com.yourteam.pgno54_hometutorbookingsystems.model;

import java.util.List;

public class Student extends AuthenticatedUser {
    private String id;
    private List<String> preferredSubjects;
    private String contactDetails;

    public Student(String id, String name, String username, String password, List<String> preferredSubjects, String contactDetails) {
        super(name, username, password);
        this.id = id;
        this.preferredSubjects = preferredSubjects;
        this.contactDetails = contactDetails;
    }

    public String getId() {
        return id;
    }

    public List<String> getPreferredSubjects() {
        return preferredSubjects;
    }

    public String getContactDetails() {
        return contactDetails;
    }

    public void setPreferredSubjects(List<String> preferredSubjects) {
        this.preferredSubjects = preferredSubjects;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    @Override
    public String getIdentifier() {
        return id;
    }

    @Override
    public String toString() {
        return id + "," + getName() + "," + getUsername() + "," + getPassword() + "," + String.join(";", preferredSubjects) + "," + contactDetails;
    }
}

package com.yourteam.pgno54_hometutorbookingsystems.model;

public class Tutor extends User {
    private String id; // Encapsulation: private fields
    private String subjectExpertise;
    private double rating;

    public Tutor(String id, String name, String subjectExpertise, double rating) {
        super(name); // Inheritance: calls User constructor
        this.id = id;
        this.subjectExpertise = subjectExpertise;
        this.rating = rating;
    }

    // Getters and setters (Information Hiding: controlled access)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjectExpertise() {
        return subjectExpertise;
    }

    public void setSubjectExpertise(String subjectExpertise) {
        this.subjectExpertise = subjectExpertise;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    // Implement abstract method (Polymorphism: method overriding)
    @Override
    public String getIdentifier() {
        return id;
    }

    @Override
    public String toString() {
        return "Tutor{id='" + id + "', name='" + getName() + "', subject='" + subjectExpertise + "', rating=" + rating + "}";
    }
}
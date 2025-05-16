package com.yourteam.pgno54_hometutorbookingsystems.model;

public abstract class User {
    private String name; // Encapsulation: private field with public methods

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Abstract method to enforce abstraction in subclasses
    public abstract String getIdentifier();
}
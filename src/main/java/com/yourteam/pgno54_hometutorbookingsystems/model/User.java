package com.yourteam.pgno54_hometutorbookingsystems.model;

public abstract class User {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getIdentifier();
}

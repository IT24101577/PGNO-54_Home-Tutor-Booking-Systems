package com.yourteam.pgno54_hometutorbookingsystems.model;

public class Admin extends AuthenticatedUser {
    public Admin(String username, String password) {
        super("Admin", username, password);
    }

    @Override
    public String getIdentifier() {
        return "";
    }
}

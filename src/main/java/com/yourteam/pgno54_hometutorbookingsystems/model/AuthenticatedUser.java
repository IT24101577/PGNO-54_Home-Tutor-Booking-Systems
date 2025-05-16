package com.yourteam.pgno54_hometutorbookingsystems.model;

public abstract class AuthenticatedUser extends User {
    private String username;
    private String password;

    public AuthenticatedUser(String name, String username, String password) {
        super(name);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

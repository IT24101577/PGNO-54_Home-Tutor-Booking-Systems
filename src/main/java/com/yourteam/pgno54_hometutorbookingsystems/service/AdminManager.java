package com.yourteam.pgno54_hometutorbookingsystems.service;

import com.yourteam.pgno54_hometutorbookingsystems.model.Admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AdminManager {
    private static final String FILE_PATH = "C:\\Project\\PGNO-54_Home-Tutor-Booking-Systems\\src\\main\\resources\\data\\admins.txt";
    private List<Admin> admins;

    public AdminManager() throws IOException {
        admins = new ArrayList<>();
        loadAdmins();
    }

    private void loadAdmins() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("admins.txt not found at: " + file.getAbsolutePath() + ", creating new file");
            file.getParentFile().mkdirs();
            file.createNewFile();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    try {
                        String username = parts[0];
                        String password = parts[1];
                        Admin admin = new Admin(username, password);
                        admins.add(admin);
                        System.out.println("Loaded admin: " + username);
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Skipping invalid line in admins file: " + line);
                }
            }
        }
    }

    public Admin authenticateAdmin(String username, String password) {
        System.out.println("Authenticating admin with username: " + username);
        return admins.stream()
                .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
}

package com.yourteam.pgno54_hometutorbookingsystems.service;

import com.yourteam.pgno54_hometutorbookingsystems.model.Tutor;
import com.yourteam.pgno54_hometutorbookingsystems.util.TutorBST;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TutorManager implements ITutorService { // Polymorphism: implements interface
    private static final String FILE_PATH = "data/tutors.txt";
    private TutorBST tutorBST; // Encapsulation: private field

    public TutorManager() throws IOException {
        tutorBST = new TutorBST();
        loadTutors();
    }

    private File getResourceFile() throws IOException {
        String resourcePath = FILE_PATH.replace("/", File.separator);
        File file = new File("C:\\Project\\HomeTutorSearchBookingSystem\\src\\main\\resources\\" + resourcePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }

    private void loadTutors() throws IOException {
        File file = getResourceFile();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int count = 0;
            System.out.println("Loading tutors from: " + file.getAbsolutePath());
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    System.out.println("Skipped empty line");
                    continue;
                }
                System.out.println("Read line: " + line);
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    try {
                        String id = parts[0].trim();
                        String name = parts[1].trim();
                        String subject = parts[2].trim().toLowerCase();
                        double rating = Double.parseDouble(parts[3].trim());
                        Tutor tutor = new Tutor(id, name, subject, rating);
                        tutorBST.insert(tutor);
                        System.out.println("Loaded tutor: ID=" + id + ", Name=" + name);
                        count++;
                    } catch (NumberFormatException e) {
                        System.err.println("Invalid rating in line: " + line);
                    }
                } else {
                    System.err.println("Invalid format in line: " + line);
                }
            }
            System.out.println("Loaded " + count + " tutors from " + file.getAbsolutePath());
        }
    }

    @Override
    public void addTutor(Tutor tutor) throws IOException {
        tutor.setSubjectExpertise(tutor.getSubjectExpertise().toLowerCase());
        List<Tutor> existingTutors = getAllTutors();

        // Log all existing tutor IDs for debugging
        System.out.println("Checking for duplicate ID: " + tutor.getId());
        System.out.println("Existing tutor IDs: " +
                existingTutors.stream().map(Tutor::getId).collect(Collectors.joining(", ")));

        // Check for duplicate ID
        boolean idExists = existingTutors.stream()
                .anyMatch(t -> t.getId().trim().equals(tutor.getId().trim()));
        if (idExists) {
            throw new IOException("A tutor with ID " + tutor.getId() + " already exists.");
        }

        // Log all existing tutor names for debugging
        System.out.println("Checking for duplicate name: " + tutor.getName());
        System.out.println("Existing tutor names: " +
                existingTutors.stream().map(Tutor::getName).collect(Collectors.joining(", ")));

        // Check for duplicate name (case-insensitive)
        boolean nameExists = existingTutors.stream()
                .anyMatch(t -> t.getName().trim().toLowerCase().equals(tutor.getName().trim().toLowerCase()));
        if (nameExists) {
            throw new IOException("A tutor with name " + tutor.getName() + " already exists.");
        }

        tutorBST.insert(tutor);
        File file = getResourceFile();
        boolean needsNewLine = false;
        if (file.length() > 0) {
            try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
                raf.seek(file.length() - 1);
                int lastByte = raf.read();
                if (lastByte != '\n' && lastByte != '\r') {
                    needsNewLine = true;
                }
            }
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (needsNewLine) {
                writer.newLine();
            }
            writer.write(tutor.getId() + "," + tutor.getName() + "," + tutor.getSubjectExpertise() + "," + tutor.getRating());
            writer.newLine();
            System.out.println("Added tutor to " + file.getAbsolutePath());
        }
    }

    @Override
    public void updateTutor(Tutor tutor) throws IOException {
        tutor.setSubjectExpertise(tutor.getSubjectExpertise().toLowerCase());
        List<Tutor> tutors = getAllTutors();
        File file = getResourceFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Tutor t : tutors) {
                if (t.getId().equals(tutor.getId())) {
                    writer.write(tutor.getId() + "," + tutor.getName() + "," + tutor.getSubjectExpertise() + "," + tutor.getRating());
                } else {
                    writer.write(t.getId() + "," + t.getName() + "," + t.getSubjectExpertise() + "," + t.getRating());
                }
                writer.newLine();
            }
            System.out.println("Updated tutors in " + file.getAbsolutePath());
        }
        tutorBST = new TutorBST();
        loadTutors();
    }

    @Override
    public void deleteTutor(String id) throws IOException {
        List<Tutor> tutors = getAllTutors();
        File file = getResourceFile();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Tutor t : tutors) {
                if (!t.getId().equals(id)) {
                    writer.write(t.getId() + "," + t.getName() + "," + t.getSubjectExpertise() + "," + t.getRating());
                    writer.newLine();
                }
            }
            System.out.println("Deleted tutor from " + file.getAbsolutePath());
        }
        tutorBST = new TutorBST();
        loadTutors();
    }

    @Override
    public List<Tutor> searchTutors(String subject, String name, double minRating) {
        System.out.println("Searching tutors - subject: " + subject + ", name: " + name + ", minRating: " + minRating);
        List<Tutor> tutors = new ArrayList<>();

        // Handle subject search using BST for exact match
        if (subject != null && !subject.isEmpty()) {
            Tutor subjectTutor = tutorBST.search(subject.toLowerCase());
            if (subjectTutor != null && subjectTutor.getRating() >= minRating) {
                tutors.add(subjectTutor);
            }
        }

        // Handle name search and combine with subject results
        if (name != null && !name.isEmpty()) {
            List<Tutor> nameMatches = getAllTutors().stream()
                    .filter(tutor -> tutor.getName().toLowerCase().contains(name.toLowerCase()))
                    .filter(tutor -> tutor.getRating() >= minRating)
                    .filter(tutor -> subject.isEmpty() || tutor.getSubjectExpertise().equals(subject.toLowerCase()))
                    .collect(Collectors.toList());
            // Add name matches, avoiding duplicates
            for (Tutor tutor : nameMatches) {
                if (!tutors.contains(tutor)) {
                    tutors.add(tutor);
                }
            }
        }

        // If no subject or name provided, return all tutors meeting minRating
        if ((subject == null || subject.isEmpty()) && (name == null || name.isEmpty())) {
            tutors = getAllTutors().stream()
                    .filter(tutor -> tutor.getRating() >= minRating)
                    .collect(Collectors.toList());
        }

        return tutors;
    }

    @Override
    public List<Tutor> getAllTutors() {
        return tutorBST.getAllTutors();
    }
}
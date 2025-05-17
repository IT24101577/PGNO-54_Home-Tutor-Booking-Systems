package com.yourteam.pgno54_hometutorbookingsystems.service;

import com.yourteam.pgno54_hometutorbookingsystems.model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StudentManager {
    private static final String FILE_PATH = "C:\\Project\\PGNO-54_Home-Tutor-Booking-Systems\\src\\main\\resources\\data\\students.txt";
    private List<Student> students;

    public StudentManager() throws IOException {
        students = new ArrayList<>();
        loadStudents();
    }

    private void loadStudents() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("students.txt not found at: " + file.getAbsolutePath() + ", creating new file");
            file.getParentFile().mkdirs();
            file.createNewFile();
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 6) {
                    try {
                        String id = parts[0];
                        String name = parts[1];
                        String username = parts[2];
                        String password = parts[3];
                        List<String> preferredSubjects = Arrays.asList(parts[4].split(";"));
                        String contactDetails = parts[5];
                        Student student = new Student(id, name, username, password, preferredSubjects, contactDetails);
                        students.add(student);
                        System.out.println("Loaded student: " + student.getId());
                    } catch (Exception e) {
                        System.err.println("Error parsing line: " + line);
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Skipping invalid line in students file: " + line);
                }
            }
        }
    }

    private void saveStudents() throws IOException {
        File file = new File(FILE_PATH);
        System.out.println("Attempting to save students to: " + file.getAbsolutePath());
        file.getParentFile().mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Student student : students) {
                writer.write(student.toString() + "\n");
                System.out.println("Saved student: " + student.getId());
            }
            System.out.println("Students successfully saved to file: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Failed to save students to file: " + file.getAbsolutePath());
            e.printStackTrace();
            throw e;
        }
    }

    public void addStudent(Student student) throws IOException {
        System.out.println("Adding student: " + student.getId());
        if (students.stream().anyMatch(s -> s.getId().equals(student.getId()))) {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists.");
        }
        students.add(student);
        saveStudents();
    }

    public void updateStudent(String id, Student updatedStudent) throws IOException {
        System.out.println("Updating student: " + id);
        students = students.stream()
                .map(s -> s.getId().equals(id) ? updatedStudent : s)
                .collect(Collectors.toList());
        saveStudents();
    }

    public void deleteStudent(String id) throws IOException {
        System.out.println("Deleting student: " + id);
        students = students.stream()
                .filter(s -> !s.getId().equals(id))
                .collect(Collectors.toList());
        saveStudents();
    }

    public Student getStudentById(String id) {
        System.out.println("Retrieving student with ID: " + id);
        return students.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Student authenticateStudent(String username, String password) {
        System.out.println("Authenticating student with username: " + username);
        return students.stream()
                .filter(s -> s.getUsername().equals(username) && s.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public List<Student> getAllStudents() {
        System.out.println("Retrieving all students");
        return new ArrayList<>(students);
    }
}

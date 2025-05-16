package com.yourteam.pgno54_hometutorbookingsystems.service;

import com.yourteam.pgno54_hometutorbookingsystems.model.Tutor;

import java.io.IOException;
import java.util.List;

public interface ITutorService {
    void addTutor(Tutor tutor) throws IOException;
    void updateTutor(Tutor tutor) throws IOException;
    void deleteTutor(String id) throws IOException;
    List<Tutor> searchTutors(String subject, String name, double minRating);
    List<Tutor> getAllTutors();
}
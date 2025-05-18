package com.yourteam.pgno54_hometutorbookingsystems.service;

import com.yourteam.pgno54_hometutorbookingsystems.model.Tutor;

import java.util.List;

public abstract class Sorter {
    public abstract void sort(List<Tutor> tutors, String criterion);

    public void printSorted(List<Tutor> tutors) {
        System.out.println("Sorted tutors: " + tutors);
    }
}
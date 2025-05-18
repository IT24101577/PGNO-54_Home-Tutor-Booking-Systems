package com.yourteam.hometutorsearchbookingsystem.service;

import com.yourteam.hometutorbookingsys.model.Tutor;

import java.util.ArrayList;
import java.util.List;

public class MergeSort extends Sorter {
    @Override
    public void sort(List<Tutor> tutors, String criterion) {
        if (tutors == null || tutors.size() <= 1) return;
        mergeSort(tutors, 0, tutors.size() - 1, criterion);
    }

    private void mergeSort(List<Tutor> tutors, int left, int right, String criterion) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(tutors, left, mid, criterion);
            mergeSort(tutors, mid + 1, right, criterion);
            merge(tutors, left, mid, right, criterion);
        }
    }

    private void merge(List<Tutor> tutors, int left, int mid, int right, String criterion) {
        // Validate indices
        if (left < 0 || right >= tutors.size() || left > right) {
            throw new IllegalArgumentException("Invalid merge range: left=" + left + ", right=" + right + ", size=" + tutors.size());
        }

        // Create a new independent list from the sublist
        List<Tutor> temp = new ArrayList<>(tutors.subList(left, right + 1));
        int i = 0, j = mid - left + 1, k = left;

        while (i <= mid - left && j <= right - left) {
            Tutor t1 = temp.get(i);
            Tutor t2 = temp.get(j);
            boolean shouldSwap = switch (criterion.toLowerCase()) {
                case "rating" -> t1.getRating() < t2.getRating();
                case "experience" -> t1.getYearsOfExperience() < t2.getYearsOfExperience();
                default -> false;
            };
            if (shouldSwap) {
                tutors.set(k++, temp.get(i++));
            } else {
                tutors.set(k++, temp.get(j++));
            }
        }
        while (i <= mid - left) tutors.set(k++, temp.get(i++));
        while (j <= right - left) tutors.set(k++, temp.get(j++));
    }
}
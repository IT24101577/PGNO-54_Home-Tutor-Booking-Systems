package com.yourteam.pgno54_hometutorbookingsystems.util;

import com.yourteam.pgno54_hometutorbookingsystems.model.Tutor;

import java.util.ArrayList;
import java.util.List;

public class TutorBST {
    private class Node {
        Tutor tutor;
        Node left, right;

        Node(Tutor tutor) {
            this.tutor = tutor;
            this.left = this.right = null;
        }
    }

    private Node root;

    public TutorBST() {
        root = null;
    }

    public void insert(Tutor tutor) {
        root = insertRec(root, tutor);
    }

    private Node insertRec(Node root, Tutor tutor) {
        if (root == null) {
            return new Node(tutor);
        }
        if (tutor.getSubjectExpertise().compareTo(root.tutor.getSubjectExpertise()) < 0) {
            root.left = insertRec(root.left, tutor);
        } else if (tutor.getSubjectExpertise().compareTo(root.tutor.getSubjectExpertise()) > 0) {
            root.right = insertRec(root.right, tutor);
        }
        return root;
    }

    public Tutor search(String subject) {
        return searchRec(root, subject);
    }

    private Tutor searchRec(Node root, String subject) {
        if (root == null || subject == null) {
            return null;
        }
        int compare = subject.compareTo(root.tutor.getSubjectExpertise());
        if (compare == 0) {
            return root.tutor;
        } else if (compare < 0) {
            return searchRec(root.left, subject);
        } else {
            return searchRec(root.right, subject);
        }
    }

    public List<Tutor> getAllTutors() {
        List<Tutor> tutors = new ArrayList<>();
        inOrderTraversal(root, tutors);
        return tutors;
    }

    private void inOrderTraversal(Node root, List<Tutor> tutors) {
        if (root != null) {
            inOrderTraversal(root.left, tutors);
            tutors.add(root.tutor);
            inOrderTraversal(root.right, tutors);
        }
    }
}
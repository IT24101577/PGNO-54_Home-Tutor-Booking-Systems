package com.yourteam.pgno54_hometutorbookingsystems.util;

import com.yourteam.pgno54_hometutorbookingsystems.model.Tutor;

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
        int compare = tutor.getSubjectExpertise().compareTo(root.tutor.getSubjectExpertise());
        if (compare < 0) {
            root.left = insertRec(root.left, tutor);
        } else {
            // If subjects are equal (compare == 0) or greater (compare > 0),
            // insert into the right subtree to allow duplicates
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
            return root.tutor; // Returns the first tutor found with this subject
        } else if (compare < 0) {
            return searchRec(root.left, subject);
        } else {
            return searchRec(root.right, subject);
        }
    }

    public void update(Tutor tutor) {
        delete(tutor.getId());
        insert(tutor); // Simplistic update by deleting and re-inserting with new data
    }

    public void delete(String id) {
        root = deleteRec(root, id);
    }

    private Node deleteRec(Node root, String id) {
        if (root == null) {
            return null;
        }

        int compare = id.compareTo(root.tutor.getId());
        if (compare < 0) {
            root.left = deleteRec(root.left, id);
        } else if (compare > 0) {
            root.right = deleteRec(root.right, id);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            Node successorParent = root;
            Node successor = root.right;
            while (successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }

            if (successorParent != root) {
                successorParent.left = successor.right;
            } else {
                successorParent.right = successor.right;
            }

            root.tutor = successor.tutor;
        }
        return root;
    }

    public Tutor[] getAllTutors() {
        // Step 1: Count the number of nodes to size the array correctly
        int size = countNodes(root);

        // Step 2: Use a fixed-size array to collect tutors
        Tutor[] tempArray = new Tutor[size];
        int[] index = new int[1]; // Use array to allow modification in recursive call
        inOrderTraversal(root, tempArray, index);

        return tempArray; // Return the array directly, avoiding ArrayList
    }

    private int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private void inOrderTraversal(Node root, Tutor[] tempArray, int[] index) {
        if (root != null) {
            inOrderTraversal(root.left, tempArray, index);
            if (index[0] < tempArray.length) {
                tempArray[index[0]++] = root.tutor;
            }
            inOrderTraversal(root.right, tempArray, index);
        }
    }
}
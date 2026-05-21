package BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Flip a binary tree
 * left child -> root
 * root -> right child
 * right child -> left child
 */
public class BinaryTreeUpsideDown {

    static class Node {
        int data;
        Node left, right;
        Node (int data) {
            this.data = data;
            left = right = null;
        }
    }

    static Node flipBinaryTree(Node root) {
        // Base case 1
        if (root == null) {
            return root;
        }
        // Base case 2
        if (root.left == null && root.right == null) {
            return root;
        }
        // Recursively call the same method
        Node flippedRoot = flipBinaryTree(root.left);
        // Rearranging main root Node after returning from recursive call
        root.left.left = root.right; // leftmost becomes root & its parent becomes its right child
        root.left.right = root;
        root.left = null;
        root.right = null;
        return flippedRoot;
    }

    static void printLevelOrder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            int nodeCount = q.size();
            while (nodeCount > 0) {
                Node node = q.poll();
                System.out.print(node.data + " ");
                if (node.left != null) {
                    q.add(node.left);
                }
                if (node.right != null) {
                    q.add(node.right);
                }
                nodeCount--;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(4);
        root.right.right = new Node(5);

        root = flipBinaryTree(root);
        printLevelOrder(root);
    }
}

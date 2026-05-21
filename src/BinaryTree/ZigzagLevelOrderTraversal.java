package BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ZigzagLevelOrderTraversal {

    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static List<Integer> zigzagTraversal(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();

        s1.push(root);
        while (!s1.isEmpty() || !s2.isEmpty()) {
            while (!s1.isEmpty()) {
                Node curr = s1.pop();
                res.add(curr.data);
                if (curr.left != null) {
                    s2.push(curr.left);
                }
                if (curr.right != null) {
                    s2.push(curr.right);
                }
            }
            while (!s2.isEmpty()) {
                Node curr = s2.pop();
                res.add(curr.data);
                if (curr.right != null) {
                    s1.push(curr.right);
                }
                if (curr.left != null) {
                    s1.push(curr.left);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Node root = new Node(20);
        root.left = new Node(8);
        root.right = new Node(22);
        root.right.right = new Node(11);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        List<Integer> res = zigzagTraversal(root);
        for (int val : res) {
            System.out.print(val + " ");
        }
    }

}

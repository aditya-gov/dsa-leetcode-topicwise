package BinaryTree;

public class Diameter {

    private static int diameter;

    static class Node {
        int data;
        Node left, right;
        Node(int data) {
            this.data = data;
            left = right = null;
        }
    }

    public static int findDiameter(Node root) {
        if (root == null) {
            return 0;
        }
        int lHeight = findDiameter(root.left);
        int rHeight = findDiameter(root.right);
        diameter = Math.max(diameter, 1 + lHeight + rHeight);
        return 1 + Math.max(lHeight, rHeight);
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.right = new Node(2);
        root.right.left = new Node(3);
        root.right.right = new Node(4);
        root.right.left.left = new Node(5);
        root.right.right.right = new Node(6);
        System.out.println("Diameter: " + findDiameter(root));
    }

}

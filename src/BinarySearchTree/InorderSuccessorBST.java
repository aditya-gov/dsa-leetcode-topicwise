package BinaryTree;

public class InorderSuccessorBST {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    static TreeNode leftMost(TreeNode root) {
        TreeNode curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    static TreeNode inorderSuccessor(TreeNode root, int target) {
        if (root == null) {
            return null;
        }
        if (root.val == target && root.right != null) {
            return leftMost(root.right);
        }
        TreeNode succ = null;
        TreeNode curr = root;
        while (curr != null) {
            if (target < curr.val) {
                succ = curr;
                curr = curr.left;
            } else if (target > curr.val) {
                curr = curr.right;
            }
        }
        return succ;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);
        int target = 14;
        //InorderSuccessorBST inorderSuccessorBST = new InorderSuccessorBST();
        System.out.println("Inorder Successor of BST: ");
        TreeNode succ = inorderSuccessor(root, target);
        if (succ != null) {
            System.out.println(succ.val);
        } else {
            System.out.println("-1");
        }
    }
}


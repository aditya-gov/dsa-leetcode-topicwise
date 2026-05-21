package BinarySearchTree;

public class LowestCommonAncestor {

    static class TreeNode {
        TreeNode left, right;
        int val;
        public TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    public static TreeNode lowestCommonAncestor(TreeNode node, int n1, int n2) {
        if (node == null) {
            return null;
        }
        if (node.val > n1 && node.val > n2) {
            return lowestCommonAncestor(node.left, n1, n2);
        }
        if (node.val < n1 && node.val < n2) {
            return lowestCommonAncestor(node.right, n1, n2);
        }
        return node;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(14);
        System.out.println("LCA(10, 14) = " +lowestCommonAncestor(root, 10, 14).val);
        System.out.println("LCA(14, 8) = " +lowestCommonAncestor(root, 14, 8).val);
        System.out.println("LCA(10, 22) = " +lowestCommonAncestor(root, 10, 22).val);
    }

}

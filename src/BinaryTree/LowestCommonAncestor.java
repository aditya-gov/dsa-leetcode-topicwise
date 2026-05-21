package BinaryTree;

public class LowestCommonAncestor {

    private static class TreeNode {
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
        // If either key matches with root data, return root
        if (node.val == n1 || node.val == n2) {
            return node;
        }

        TreeNode left_lca = lowestCommonAncestor(node.left, n1, n2);
        TreeNode right_lca = lowestCommonAncestor(node.right, n1, n2);

        // If both of the above calls return Non-NULL, then one
        // node is present in one subtree and the other is present
        // in the other, so this node is the LCA
        if (left_lca != null && right_lca != null) {
            return node;
        }
        // Otherwise check if left subtree or right subtree is LCA
        return left_lca != null ? left_lca : right_lca;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        System.out.println("LCA(4, 5) = " +lowestCommonAncestor(root, 4, 5).val);
        System.out.println("LCA(4, 6) = " +lowestCommonAncestor(root, 4, 6).val);
        System.out.println("LCA(3, 4) = " +lowestCommonAncestor(root, 3, 4).val);
        System.out.println("LCA(2, 4) = " +lowestCommonAncestor(root, 2, 4).val);
    }

}

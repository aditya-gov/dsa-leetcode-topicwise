package BinaryTree;

public class MaxPathSum {
    int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        int sum = findMaxPathSum(root);
        return Math.max(sum, res);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    private int findMaxPathSum(TreeNode root){
        if(root == null) {
            return 0;
        }
        int leftSum = findMaxPathSum(root.left);
        int rightSum = findMaxPathSum(root.right);
        res = Math.max(res, leftSum + rightSum + root.val);
        int currentMax = Math.max(0, Math.max(leftSum, rightSum)) + root.val;
        res = Math.max(res, currentMax);
        return currentMax;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        MaxPathSum maxPathSum = new MaxPathSum();
        System.out.println(maxPathSum.maxPathSum(root));
    }
}



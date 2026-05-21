package BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class FindLeavesBinaryTree {

    private final List<List<Integer>> result;

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
            left = right = null;
        }
    }

    public FindLeavesBinaryTree() {
        result = new ArrayList<>();
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        calculateHeightAndCollectNodes(root);
        return result;
    }

    private int calculateHeightAndCollectNodes(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftH = calculateHeightAndCollectNodes(root.left);
        int rightH = calculateHeightAndCollectNodes(root.right);
        int currH = Math.max(leftH, rightH);
        if (result.size() == currH) {
            result.add(new ArrayList<>());
        }
        result.get(currH).add(root.val);
        return currH + 1;
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        FindLeavesBinaryTree findLeavesBinaryTree = new FindLeavesBinaryTree();
        System.out.println(findLeavesBinaryTree.findLeaves(node));
    }

}

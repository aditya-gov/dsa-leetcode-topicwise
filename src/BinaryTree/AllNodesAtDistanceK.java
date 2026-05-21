package BinaryTree;

import java.util.*;

public class AllNodesAtDistanceK {

    private static class TreeNode {
        int val;
        TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = right = null;
        }
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        // hash map to map the nodes to their parent nodes.
        Map<TreeNode, TreeNode> hm = new HashMap<>();
        hm.put(root, null);
        TreeNode tar = null;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            if (curr.val == target.val) {
                tar = curr;
            }
            if (curr.left != null) {
                hm.put(curr.left, curr);
                q.add(curr.left);
            }
            if (curr.right != null) {
                hm.put(curr.right, curr);
                q.add(curr.right);
            }
        }
        q.add(tar);

        // hash map to check if a node has been visited.
        Map<TreeNode, Boolean> visited = new HashMap<>();
        while (!q.isEmpty()) {
            int size = q.size();
            // If the distance is k, then append the elements into result.
            if (k == 0) {
                while (!q.isEmpty()) {
                    TreeNode node = q.poll();
                    res.add(node.val);
                }
                break;
            }
            while (size-- > 0) {
                TreeNode curr = q.poll();
                visited.put(curr, true);
                if (curr.left != null && !visited.getOrDefault(curr.left, false)) {
                    q.add(curr.left);
                }
                if (curr.right != null && !visited.getOrDefault(curr.right, false)) {
                    q.add(curr.right);
                }
                TreeNode parent = hm.get(curr);
                if (parent != null && !visited.getOrDefault(parent, false)) {
                    q.add(parent);
                }
            }
            k--;
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(7);
        root.right = new TreeNode(24);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(3);
        root.left.right.left = new TreeNode(1);
        TreeNode target = new TreeNode(7);
        int k = 2;
        System.out.println(distanceK(root, target, k));
    }
}

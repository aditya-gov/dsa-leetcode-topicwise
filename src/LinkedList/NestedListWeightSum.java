package LinkedList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NestedListWeightSum {

    static class Node {
        int val, depth;
        Node(int val, int depth) {
            this.val = val;
            this.depth = depth;
        }
    }

    public static int depthSumInverse(List<Object> nestedList) {
        List<Node> flatList = new ArrayList<>();
        int[] maxDepth = new int[1];
        flatten(nestedList, 0, flatList, maxDepth);
        int sum = 0;
        for (Node node : flatList) {
            sum += node.val * (maxDepth[0] + 1 - node.depth);
        }
        return sum;
    }

    private static void flatten(List<Object> nestedList, int depth, List<Node> flatList, int[] maxDepth) {
        for (Object object : nestedList) {
            if (object instanceof Integer) {
                flatList.add(new Node((Integer) object, depth));
                maxDepth[0] = Math.max(maxDepth[0], depth);
            } else {
                flatten((List<Object>) object, depth + 1, flatList, maxDepth);
            }
        }
    }

    public static void main(String[] args) {
        List<Object> nestedList1 = Arrays.asList(Arrays.asList(1, 1), 2, Arrays.asList(1, 1));
        System.out.println(depthSumInverse(nestedList1));
        List<Object> nestedList2 = Arrays.asList(1, Arrays.asList(3, Arrays.asList(5)));
        System.out.println(depthSumInverse(nestedList2));
    }
}

package graph.topologicalSort;

import java.util.*;

/**
 * You are managing a cloud infrastructure or database system where resources have hierarchical dependencies.
 * You are given an array of unique strings resources representing all the active resources in the system, and a 2D array of strings dependencies.
 * Each dependency is represented as a pair [Parent, Child], which dictates a strict referential integrity constraint:
 * A parent resource cannot be deleted until all of its child resources have been completely deleted first.
 * Determine a valid sequence in which every resource can be safely deleted without triggering a constraint violation.
 * Return this sequence as an array of strings. If it is impossible to delete all resources due to a circular dependency (a cycle), return an empty array.
 */
public class SafeResourceDeletionSequence {

    public String[] findSafeDeletionOrder(String[] resources, String[][] dependencies) {
        if (resources == null || resources.length == 0) {
            return new String[0];
        }
        Map<String, List<String>> parentOf = new HashMap<>();

        // To track how many active children a parent resource currently has.
        Map<String, Integer> outDegree = new HashMap<>();

        for (String resource : resources) {
            parentOf.put(resource, new ArrayList<>());
            outDegree.put(resource, 0);
        }
        if (dependencies != null) {
            for (String[] dependency : dependencies) {
                String parent = dependency[0];
                String child = dependency[1];
                parentOf.get(child).add(parent);
                outDegree.put(parent, outDegree.get(parent) + 1);
            }
        }

        Queue<String> queue = new LinkedList<>();
        for (String resource : resources) {
            if (outDegree.get(resource) == 0) {
                queue.offer(resource);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            String current = queue.poll();
            sb.append(current);
            sb.append(",");

            // Notify all parents that this child has been safely deleted
            for (String parent : parentOf.get(current)) {
                outDegree.put(parent, outDegree.get(parent) - 1);
                // If the parent now has 0 children, it is safe to delete
                if (outDegree.get(parent) == 0) {
                    queue.offer(parent);
                }
            }
        }

        String[] safeDeletionOrder = sb.toString().split(",");
        //System.out.println(Arrays.toString(safeDeletionOrder));
        if (safeDeletionOrder.length == resources.length) {
            return safeDeletionOrder;
        } else {
            return new String[0];
        }
    }

    public static void main(String[] args) {
        String[] resources = {"Master_Account", "Netflix_Password", "Bank_Password", "Bank_2FA_Token"};
        String[][] dependencies = {{"Master_Account", "Netflix_Password"}, {"Master_Account", "Bank_Password"}, {"Bank_Password", "Bank_2FA_Token"}};
        SafeResourceDeletionSequence solution = new SafeResourceDeletionSequence();
        System.out.println(Arrays.toString(solution.findSafeDeletionOrder(resources, dependencies)));
    }
}

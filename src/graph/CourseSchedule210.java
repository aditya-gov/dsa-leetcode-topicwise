package graph;

import java.util.*;

public class CourseSchedule210 {

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        if (numCourses == 0) {
            return res;
        }
        Map<Integer, List<Integer>> adjList = createdAdjacencyList(numCourses, prerequisites);
        boolean[] visited = new boolean[numCourses];
        boolean[] recStack = new boolean[numCourses];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < numCourses; i++) {
            boolean cycle = dfs(i, visited, recStack, adjList, stack);
            if (cycle) {
                return new int[] {};
            }
        }
        int index = 0;
        while (!stack.isEmpty()) {
            res[index++] = stack.pop();
        }
        return res;
    }

    private static boolean dfs(int course, boolean[] visited, boolean[] recStack, Map<Integer, List<Integer>> adjList, Stack<Integer> stack) {
        if (recStack[course]) {
            return true;
        }
        if (visited[course]) {
            return false;
        }
        visited[course] = true;
        recStack[course] = true;
        for (int adj : adjList.get(course)) {
            boolean cycle = dfs(adj, visited, recStack, adjList, stack);
            if (cycle) {
                return true;
            }
        }
        recStack[course] = false;
        stack.push(course);
        return false;
    }

    private static Map<Integer, List<Integer>> createdAdjacencyList(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adj = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            adj.put(i, new ArrayList<>());
        }
        for (int[] prerequisite : prerequisites) {
            adj.get(prerequisite[1]).add(prerequisite[0]);
        }
        return adj;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = new int[][] {{1,0}, {2,0}, {3,1}, {3,2}};
        System.out.println(Arrays.toString(findOrder(numCourses, prerequisites)));
    }

}

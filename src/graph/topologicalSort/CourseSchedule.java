package graph.topologicalSort;

import java.util.LinkedList;
import java.util.Queue;

public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[][] mat = new int[numCourses][numCourses];
        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int ready = prerequisite[0];
            int pre = prerequisite[1];
            if (mat[pre][ready] == 0) {
                inDegree[ready]++;
            }
            mat[pre][ready] = 1;
        }

        int count = 0;
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        while (!q.isEmpty()) {
            int course = q.poll(); // course being the pre-requisite
            count++;
            for (int i = 0; i < numCourses; i++) {
                if (mat[course][i] != 0) { // i here being the course to opt
                    if (--inDegree[i] == 0) {
                        q.add(i);
                    }
                }
            }
        }
        return count == numCourses;
    }

    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = new int[][] {{1, 0}, {0, 1}};
        System.out.println(canFinish(numCourses, prerequisites));
    }

}

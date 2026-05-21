package TwoPointers;

public class CelebrityProblem {

    public static int findCelebrity(int[][] matrix) {
        int n = matrix.length;
        int i = 0, j = n - 1;

        while (i < j) {
            // j knows i so j can't be a celebrity
            if (matrix[j][i] == 1) {
                j--;
            } else {
                i++;
            }
        }
        // i points to c (a celebrity candidate)
        int c = i;
        for (i = 0; i < n; i++) {
            if (i == c) {
                continue;
            }
            // if c doesn't know any person or any person doesn't know c
            if (matrix[c][i] != 0 || matrix[i][c] == 0) {
                return -1;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        int[][] matrix = { { 1, 1, 0 },
                { 0, 1, 0 },
                { 0, 1, 1 } };
        System.out.println(findCelebrity(matrix));
    }

}

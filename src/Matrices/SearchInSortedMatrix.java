package Matrices;

public class SearchInSortedMatrix {

    public static boolean findElement(int[][] matrix, int target) {
        int m = matrix.length; //row
        if(m == 0) {
            return false;
        }
        int n = matrix[0].length; //col
        if(n == 0) {
            return false;
        }
        if(target > matrix[m-1][n-1]) {
            return false;
        }

        int i = 0, j = n - 1;
        while(i >= 0 && i < m && j >= 0 && j < n) {
            if(matrix[i][j] == target) {
                return true;
            }
            if(matrix[i][j] > target) {
                j--;
            } else {
                i++;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22},{10,13,14,17,24},{18,21,23,26,30}};
        int target = 5;
        System.out.println(findElement(matrix, target));
    }

}

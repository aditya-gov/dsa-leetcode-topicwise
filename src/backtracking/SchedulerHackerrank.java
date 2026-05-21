package backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SchedulerHackerrank {

    private final List<String> result = new ArrayList<>();

    public List<String> generateScheduler(int workHours, int dayHours, String pattern) {
        int filledHours = 0;
        int unscheduledDays = 0;

        for (char c : pattern.toCharArray()) {
            if (Character.isDigit(c)) {
                filledHours += Character.getNumericValue(c);
            } else {
                unscheduledDays++;
            }
        }

        int remainingHours = workHours - filledHours;
        this.generateSchedulerUtil(0, 0, new StringBuilder(pattern), unscheduledDays, remainingHours, dayHours);
        Collections.sort(result);
        return result;
    }

    private void generateSchedulerUtil(int dayIdx, int currentHrs, StringBuilder currentPattern,
                                       int unscheduledDays, int remainingHours, int dayHours) {
        if (unscheduledDays == 0) {
            if (currentHrs == remainingHours) {
                result.add(currentPattern.toString());
            }
            return;
        }
        if (currentHrs > remainingHours) {
            return;
        }
        int start = dayIdx;
        while (start < currentPattern.length() && currentPattern.charAt(start) != '?') {
            start++;
        }
        if (start == currentPattern.length()) {
            if (currentHrs == remainingHours) {
                result.add(currentPattern.toString());
            }
            return;
        }
        for (int i = 0; i <= dayHours; i++) {
            currentPattern.setCharAt(start, (char) ('0' + i));
            this.generateSchedulerUtil(start + 1, currentHrs + i, currentPattern, unscheduledDays - 1, remainingHours, dayHours);
            currentPattern.setCharAt(start, '?');
        }
    }

    /*private void generatePermutations(List<List<Integer>> permutations, int workHours, int unknown) {
        for (int i = 0; i < workHours; i++) {
            List<Integer> permutation = new ArrayList<>();
            permutations.add(Collections.singletonList(i));
            generatePermutationsUtil(permutations, permutation, workHours - i, unknown - 1);
        }
    }

    private void generatePermutationsUtil(List<List<Integer>> permutations, List<Integer> permutation,
                                          int remainingHours, int remainingShifts) {
        if (remainingHours == 0 && remainingShifts == 0) {
            permutations.add(permutation);
            return;
        }
        if (remainingHours <= 0 || remainingShifts <= 0) {
            return;
        }
        for (int i = 0; i <= remainingHours; i++) {
            List<Integer> newPermutation = new ArrayList<>(permutation);
            newPermutation.add(i);
            generatePermutationsUtil(permutations, newPermutation, remainingHours - i, remainingShifts - 1);
        }
    }*/

    public static void main(String[] args) {
        String pattern = "88??";
        int workHours = 24;
        int dayHours = 8;
        SchedulerHackerrank schedulerHackerrank = new SchedulerHackerrank();
        System.out.println(schedulerHackerrank.generateScheduler(workHours, dayHours, pattern));
    }
}

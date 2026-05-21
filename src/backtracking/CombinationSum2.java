package backtracking;

import java.util.*;

public class CombinationSum2 {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Set<List<Integer>> hs = new HashSet<>();
        Arrays.sort(candidates);
        this.dfs(hs, new ArrayList<>(), candidates, 0, target);
        return new ArrayList<>(hs);
    }

    private void dfs(Set<List<Integer>> hs, List<Integer> list, int[] candidates, int idx, int target) {
        if (target == 0) {
            hs.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (target < 0) {
                return;
            }
            list.add(candidates[i]);
            this.dfs(hs, list, candidates, i + 1, target - candidates[i]); // i ensuring no repetition allowed
            list.remove(list.indexOf(candidates[i])); // ensuring no duplicates allowed
        }
    }

    public static void main(String[] args) {
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        CombinationSum2 combinationSum2 = new CombinationSum2();
        System.out.println("No of unique combinations: " + combinationSum2.combinationSum2(candidates, target));
        int[] candidates1 = {3, 2, 5, 1, 2, 4};
        int target1 = 5;
        System.out.println("No of unique combinations: " + combinationSum2.combinationSum2(candidates1, target1));
    }


}

package backtracking;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        this.dfs(res, new ArrayList<>(), candidates, 0, target);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> list, int[] candidates, int idx, int target) {
        if (target == 0) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (target < 0) {
                return;
            }
            list.add(candidates[i]);
            dfs(res, list, candidates, i, target - candidates[i]); // i ensuring repetition allowed
            list.remove(list.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        CombinationSum combinationSum = new CombinationSum();
        System.out.println("No of unique combinations: " + combinationSum.combinationSum(candidates, target));
        int[] candidates1 = {5, 5, 1};
        int target1 = 6;
        System.out.println("No of unique combinations: " + combinationSum.combinationSum(candidates1, target1));
    }

}

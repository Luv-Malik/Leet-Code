import java.util.*;

class Solution {
    private record Interval(int left, int right, long weight, int originalIndex) {}
    private record State(long weight, List<Integer> selected) {}

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        List<Interval> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            list.add(new Interval(
                intervals.get(i).get(0), 
                intervals.get(i).get(1), 
                intervals.get(i).get(2), 
                i
            ));
        }
        
        // Sort intervals primary by left boundary
        list.sort(Comparator.comparingInt((Interval a) -> a.left));
        
        State[][] memo = new State[n][5];
        State bestResult = dp(list, memo, 0, 4);
        
        return bestResult.selected.stream().mapToInt(Integer::intValue).toArray();
    }

    private State dp(List<Interval> intervals, State[][] memo, int i, int quota) {
        if (i == intervals.size() || quota == 0) {
            return new State(0, List.of());
        }
        
        if (memo[i][quota] != null) {
            return memo[i][quota];
        }

        // Option 1: Skip current interval
        State skip = dp(intervals, memo, i + 1, quota);

        // Option 2: Pick current interval
        Interval curr = intervals.get(i);
        int nextIdx = findNextValid(intervals, i + 1, curr.right);
        State nextState = dp(intervals, memo, nextIdx, quota - 1);

        List<Integer> pickSelected = new ArrayList<>(nextState.selected);
        pickSelected.add(curr.originalIndex);
        Collections.sort(pickSelected); // Keep indices sorted for valid comparisons

        State pick = new State(curr.weight + nextState.weight, pickSelected);

        // Tie-breaker logic for dynamic programming
        State best;
        if (pick.weight > skip.weight) {
            best = pick;
        } else if (skip.weight > pick.weight) {
            best = skip;
        } else {
            // Equal weights: choose lexicographically smaller index list
            if (compareLists(pick.selected, skip.selected) < 0) {
                best = pick;
            } else {
                best = skip;
            }
        }

        return memo[i][quota] = best;
    }

    // Binary search to find first interval with left > rightBoundary
    private int findNextValid(List<Interval> intervals, int startFrom, int rightBoundary) {
        int l = startFrom;
        int r = intervals.size();
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (intervals.get(mid).left > rightBoundary) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    // Lexicographical comparison for two Integer lists
    private int compareLists(List<Integer> list1, List<Integer> list2) {
        int minLen = Math.min(list1.size(), list2.size());
        for (int i = 0; i < minLen; i++) {
            int cmp = Integer.compare(list1.get(i), list2.get(i));
            if (cmp != 0) return cmp;
        }
        return Integer.compare(list1.size(), list2.size());
    }
}
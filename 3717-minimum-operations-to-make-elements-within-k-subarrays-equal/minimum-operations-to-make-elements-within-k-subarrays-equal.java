import java.util.*;

class Solution {
    
    static class WindowTracker {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        long sum = 0;
        int size = 0;
        
        void add(int val) {
            map.put(val, map.getOrDefault(val, 0) + 1);
            sum += val;
            size++;
        }
        
        void remove(int val) {
            int count = map.get(val);
            if (count == 1) {
                map.remove(val);
            } else {
                map.put(val, count - 1);
            }
            sum -= val;
            size--;
        }
        
        int lastKey() {
            return map.lastKey();
        }
        
        int firstKey() {
            return map.firstKey();
        }
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] minOps = new long[n - x + 1];
        
        WindowTracker lower = new WindowTracker();
        WindowTracker upper = new WindowTracker();
        
        for (int i = 0; i < n; i++) {
            if (lower.size == 0 || nums[i] <= lower.lastKey()) {
                lower.add(nums[i]);
            } else {
                upper.add(nums[i]);
            }
            
            if (i >= x) {
                int outNum = nums[i - x];
                if (lower.map.containsKey(outNum)) {
                    lower.remove(outNum);
                } else {
                    upper.remove(outNum);
                }
            }
            
            if (lower.size < upper.size) {
                int val = upper.firstKey();
                upper.remove(val);
                lower.add(val);
            } else if (lower.size - upper.size > 1) {
                int val = lower.lastKey();
                lower.remove(val);
                upper.add(val);
            }
            
            if (i >= x - 1) {
                int median = lower.lastKey();
                long ops = ((long) median * lower.size - lower.sum) + (upper.sum - (long) median * upper.size);
                minOps[i - x + 1] = ops;
            }
        }
        
        long[][] dp = new long[n - x + 2][k + 1];
        for (long[] row : dp) {
            Arrays.fill(row, Long.MAX_VALUE / 2);
        }
        
        for (int i = 0; i <= n - x + 1; i++) {
            dp[i][0] = 0;
        }
        
        int numWindows = n - x + 1;
        for (int j = 1; j <= k; j++) {
            long minSoFar = Long.MAX_VALUE / 2;
            for (int i = 1; i <= numWindows; i++) {
                int prevIdx = Math.max(0, i - x);
                minSoFar = Math.min(minSoFar, dp[prevIdx][j - 1]);
                dp[i][j] = Math.min(dp[i - 1][j], minSoFar + minOps[i - 1]);
            }
        }
        
        return dp[numWindows][k];
    }
}
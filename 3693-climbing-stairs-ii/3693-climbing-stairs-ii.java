import java.util.Arrays;

class Solution {
    public int climbStairs(int n, int[] costs) {
        int[] dp = new int[n + 1];
        final int INF = Integer.MAX_VALUE / 2;
        Arrays.fill(dp, INF);
        
        // Base case: starting at step 0 with cost 0
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            int currentCost = costs[i - 1];
            // Look back up to 3 steps (i - 3, i - 2, i - 1)
            for (int j = Math.max(0, i - 3); j < i; j++) {
                dp[i] = Math.min(dp[i], dp[j] + currentCost + (i - j) * (i - j));
            }
        }
        
        return dp[n];
    }
}
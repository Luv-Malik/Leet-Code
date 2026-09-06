class Solution {
    public int numDistinct(String s, String t) {
        int n = s.length();
        int m = t.length();
        
        // dp[j] stores number of distinct subsequences matching t[0...j-1]
        int[] dp = new int[m + 1];
        dp[0] = 1; // Base case: 1 way to form an empty t
        
        for (int i = 1; i <= n; i++) {
            for (int j = m; j >= 1; j--) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        return dp[m];
    }
}
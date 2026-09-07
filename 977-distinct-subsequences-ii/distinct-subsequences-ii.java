class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        
        // dp[i] stores the number of distinct subsequences ending with character ('a' + i)
        long[] dp = new long[26];
        long totalSum = 0; // Tracks the sum of all elements in dp array
        
        for (char c : s.toCharArray()) {
            int idx = c - 'a';
            
            // New count for subsequences ending in 'c' is (1 + totalSum)
            long newCount = (1 + totalSum) % MOD;
            
            // Update totalSum: subtract old dp[idx] value and add newCount
            totalSum = (totalSum - dp[idx] + newCount + MOD) % MOD;
            
            // Update dp[idx]
            dp[idx] = newCount;
        }
        
        return (int) totalSum;
    }
}
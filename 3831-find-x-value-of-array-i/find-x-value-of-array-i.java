class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];
        // dp[r] holds the number of subarrays ending at the previous index with product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];
            int numMod = num % k;

            // 1. Start a new subarray consisting solely of `num`
            newDp[numMod]++;

            // 2. Extend all subarrays ending at the previous index
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int nextMod = (int) ((1L * r * numMod) % k);
                    newDp[nextMod] += dp[r];
                }
            }

            // 3. Accumulate subarray counts for the global answer
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}
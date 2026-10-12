import java.util.List;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        long MOD = 1_000_000_007L;
        int[] bitCounts = new int[31];

        // Step 1: Count frequency of set bits at each position
        for (int num : nums) {
            for (int i = 0; i < 31; i++) {
                if (((num >> i) & 1) == 1) {
                    bitCounts[i]++;
                }
            }
        }

        long totalSumOfSquares = 0;

        // Step 2: Greedily build the k largest numbers
        for (int j = 0; j < k; j++) {
            long currentNum = 0;
            for (int i = 0; i < 31; i++) {
                if (bitCounts[i] > 0) {
                    currentNum |= (1L << i);
                    bitCounts[i]--;
                }
            }

            // Step 3: Add square of current number modulo 10^9 + 7
            long square = (currentNum * currentNum) % MOD;
            totalSumOfSquares = (totalSumOfSquares + square) % MOD;
        }

        return (int) totalSumOfSquares;
    }
}
import java.util.List;
import java.util.Arrays;

class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int minSum = Integer.MAX_VALUE;
        
        // Iterate over all possible starting points of the subarray
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            // Extend the subarray to the right
            for (int j = i; j < n; j++) {
                currentSum += nums.get(j);
                int length = j - i + 1;
                
                // Check if the length is within [l, r] and the sum is positive
                if (length >= l && length <= r && currentSum > 0) {
                    minSum = Math.min(minSum, currentSum);
                }
            }
        }
        
        // If minSum wasn't updated, no valid subarray exists, return -1
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}
import java.util.function.IntPredicate;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;
        
        // Find the index of 0
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }
        
        final int finalZero = zero;
        
        // Helper lambda to check if elements are sorted with a given step
        IntPredicate check = step -> {
            for (int i = 1; i < n; i++) {
                int prev = (finalZero + (i - 1) * step + n) % n;
                int curr = (finalZero + i * step + n) % n;
                if (nums[prev] > nums[curr]) {
                    return false;
                }
            }
            return true;
        };
        
        int ans = Integer.MAX_VALUE;
        
        // Check step = 1 (increasing order to the right)
        if (check.test(1)) {
            ans = Math.min(ans, zero);         // Just left rotate `zero` times
            ans = Math.min(ans, n - zero + 2); // Reverse, rotate, reverse back
        }
        
        // Check step = -1 (increasing order to the left)
        if (check.test(-1)) {
            ans = Math.min(ans, zero + 2);     // Rotate, then reverse
            ans = Math.min(ans, n - zero);     // Reverse, then rotate
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
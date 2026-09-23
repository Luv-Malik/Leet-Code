class Solution {
    public int minOperations(int[] nums, int x) {
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;
        
        // Impossible to reduce x to zero
        if (target < 0) {
            return -1;
        }
        
        // Needs all elements
        if (target == 0) {
            return nums.length;
        }

        int currentSum = 0;
        int maxLen = -1;
        int left = 0;

        // Sliding window to find longest subarray summing to target
        for (int right = 0; right < nums.length; right++) {
            currentSum += nums[right];

            // Shrink window if currentSum exceeds target
            while (currentSum > target && left <= right) {
                currentSum -= nums[left];
                left++;
            }

            // Check if we reached target sum
            if (currentSum == target) {
                maxLen = Math.max(maxLen, right - left + 1);
            }
        }

        return maxLen == -1 ? -1 : nums.length - maxLen;
    }
}
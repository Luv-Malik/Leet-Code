class Solution {
    public int sortPermutation(int[] nums) {
        int ans = -1;
        for (int i = 0; i < nums.length; i++) {
            // Check if the element is misplaced
            if (nums[i] != i) {
                if (ans == -1) {
                    ans = nums[i];
                } else {
                    ans &= nums[i];
                }
            }
        }
        // If all elements are already sorted, ans remains -1, so return 0
        return Math.max(ans, 0);
    }
}
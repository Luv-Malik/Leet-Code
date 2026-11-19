import java.util.Arrays;

class Solution {
    public int minimizeSum(int[] nums) {
        int n = nums.length;
        if (n <= 3) {
            return 0;
        }

        Arrays.sort(nums);

        // 1. Change the two smallest elements -> remaining min is nums[2], max is nums[n - 1]
        int changeTwoSmallest = nums[n - 1] - nums[2];

        // 2. Change the two largest elements -> remaining min is nums[0], max is nums[n - 3]
        int changeTwoLargest = nums[n - 3] - nums[0];

        // 3. Change one smallest and one largest -> remaining min is nums[1], max is nums[n - 2]
        int changeBothEnds = nums[n - 2] - nums[1];

        return Math.min(changeTwoSmallest, Math.min(changeTwoLargest, changeBothEnds));
    }
}
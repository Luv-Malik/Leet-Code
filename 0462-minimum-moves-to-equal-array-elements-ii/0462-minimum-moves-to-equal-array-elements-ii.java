import java.util.Arrays;

class Solution {
    public int minMoves2(int[] nums) {
        // Step 1: Sort the array to easily find the median
        Arrays.sort(nums);
        
        int median = nums[nums.length / 2];
        int moves = 0;
        
        // Step 2: Calculate the absolute difference from the median for each element
        for (int num : nums) {
            moves += Math.abs(num - median);
        }
        
        return moves;
    }
}
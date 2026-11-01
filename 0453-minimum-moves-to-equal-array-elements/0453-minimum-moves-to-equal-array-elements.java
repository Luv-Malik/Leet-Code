class Solution {
    public int minMoves(int[] nums) {
        // Step 1: Find the minimum element in the array
        int min = nums[0];
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
        }
        
        // Step 2: Sum up the differences between each element and the minimum
        int moves = 0;
        for (int num : nums) {
            moves += num - min;
        }
        
        return moves;
    }
}
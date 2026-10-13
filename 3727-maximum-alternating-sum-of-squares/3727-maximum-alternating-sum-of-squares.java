import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        int n = nums.length;
        long[] squares = new long[n];
        
        // Step 1: Compute the square of each element
        for (int i = 0; i < n; i++) {
            squares[i] = (long) nums[i] * nums[i];
        }
        
        // Step 2: Sort the squared values in ascending order
        Arrays.sort(squares);
        
        long score = 0;
        int mid = n / 2;
        
        // Step 3: Subtract the smaller half of the squares
        for (int i = 0; i < mid; i++) {
            score -= squares[i];
        }
        
        // Step 4: Add the larger half of the squares
        for (int i = mid; i < n; i++) {
            score += squares[i];
        }
        
        return score;
    }
}
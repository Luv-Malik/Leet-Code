import java.util.Arrays;

class Solution {
    public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
        // Sort arr2 so we can use binary search
        Arrays.sort(arr2);
        int ans = 0;
        
        for (int x : arr1) {
            int target = x - d;
            int i = Arrays.binarySearch(arr2, target);
            
            // If the exact value isn't found, binarySearch returns (-(insertion point) - 1)
            if (i < 0) {
                i = -i - 1;
            }
            
            // Check if the first element >= (x - d) is strictly greater than (x + d)
            // If it is, then no element in arr2 falls within the range [x - d, x + d]
            if (i == arr2.length || arr2[i] > x + d) {
                ans++;
            }
        }
        
        return ans;
    }
}
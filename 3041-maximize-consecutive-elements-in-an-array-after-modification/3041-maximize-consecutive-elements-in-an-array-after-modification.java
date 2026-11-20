import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);

        // dp.get(x) = longest consecutive sequence ending with value x
        Map<Integer, Integer> dp = new HashMap<>();
        int maxLen = 0;

        for (int num : nums) {
            // Option 1: Increment num -> num + 1, extending sequence ending at num
            int extInc = dp.getOrDefault(num, 0) + 1;
            dp.put(num + 1, extInc);

            // Option 2: Keep num as num, extending sequence ending at num - 1
            int extKeep = dp.getOrDefault(num - 1, 0) + 1;
            dp.put(num, extKeep);

            maxLen = Math.max(maxLen, Math.max(extInc, extKeep));
        }

        return maxLen;
    }
}
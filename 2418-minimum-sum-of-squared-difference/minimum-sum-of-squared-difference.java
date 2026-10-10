class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        int[] d = new int[n];
        long totalDiffSum = 0;
        int maxDiff = 0;
        int k = k1 + k2;

        // Step 1: Compute absolute differences and total sum
        for (int i = 0; i < n; ++i) {
            d[i] = Math.abs(nums1[i] - nums2[i]);
            totalDiffSum += d[i];
            maxDiff = Math.max(maxDiff, d[i]);
        }

        // If total operations can reduce all differences to 0
        if (totalDiffSum <= k) {
            return 0;
        }

        // Step 2: Binary search for the optimal maximum difference threshold
        int left = 0, right = maxDiff;
        while (left < right) {
            int mid = left + (right - left) / 2;
            long operationsNeeded = 0;
            for (int v : d) {
                if (v > mid) {
                    operationsNeeded += (v - mid);
                }
            }
            if (operationsNeeded <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // Step 3: Apply the threshold reduction
        for (int i = 0; i < n; ++i) {
            if (d[i] > left) {
                k -= (d[i] - left);
                d[i] = left;
            }
        }

        // Step 4: Distribute any remaining operations decrementing elements equal to 'left'
        for (int i = 0; i < n && k > 0; ++i) {
            if (d[i] == left) {
                d[i]--;
                k--;
            }
        }

        // Step 5: Calculate the final minimum sum of squared differences
        long minSumSquares = 0;
        for (int v : d) {
            minSumSquares += (long) v * v;
        }

        return minSumSquares;
    }
}
import java.util.HashSet;
import java.util.Set;

class Solution {
    private static final long BASE1 = 100_003L;
    private static final long MOD1 = 1_000_000_007L;
    private static final long BASE2 = 100_019L;
    private static final long MOD2 = 1_000_000_009L;

    public int smallestUniqueSubarray(int[] nums) {
        int n = nums.length;
        long[] pow1 = new long[n + 1];
        long[] pow2 = new long[n + 1];
        pow1[0] = 1;
        pow2[0] = 1;

        for (int i = 1; i <= n; i++) {
            pow1[i] = (pow1[i - 1] * BASE1) % MOD1;
            pow2[i] = (pow2[i - 1] * BASE2) % MOD2;
        }

        int low = 1;
        int high = n;
        int ans = n;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (hasUniqueSubarrayOfLength(nums, mid, pow1, pow2)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean hasUniqueSubarrayOfLength(int[] nums, int len, long[] pow1, long[] pow2) {
        int n = nums.length;
        long h1 = 0;
        long h2 = 0;

        for (int i = 0; i < len; i++) {
            h1 = (h1 * BASE1 + nums[i]) % MOD1;
            h2 = (h2 * BASE2 + nums[i]) % MOD2;
        }

        Set<Long> seenOnce = new HashSet<>();
        Set<Long> seenMore = new HashSet<>();
        recordHash(pack(h1, h2), seenOnce, seenMore);

        for (int i = 1; i <= n - len; i++) {
            long leftOut1 = ((nums[i - 1] % MOD1) * pow1[len - 1]) % MOD1;
            h1 = (h1 - leftOut1 + MOD1) % MOD1;
            h1 = (h1 * BASE1 + nums[i + len - 1]) % MOD1;

            long leftOut2 = ((nums[i - 1] % MOD2) * pow2[len - 1]) % MOD2;
            h2 = (h2 - leftOut2 + MOD2) % MOD2;
            h2 = (h2 * BASE2 + nums[i + len - 1]) % MOD2;

            recordHash(pack(h1, h2), seenOnce, seenMore);
        }

        return !seenOnce.isEmpty();
    }

    private void recordHash(long key, Set<Long> seenOnce, Set<Long> seenMore) {
        if (seenMore.contains(key)) {
            return;
        }
        if (seenOnce.contains(key)) {
            seenOnce.remove(key);
            seenMore.add(key);
        } else {
            seenOnce.add(key);
        }
    }

    private long pack(long h1, long h2) {
        return (h1 << 32) | (h2 & 0xffffffffL);
    }
}
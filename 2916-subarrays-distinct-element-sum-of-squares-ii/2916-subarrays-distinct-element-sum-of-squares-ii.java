import java.util.HashMap;
import java.util.Map;

class Solution {
    private static final int MOD = 1_000_000_007;

    class SegmentTree {
        int n;
        long[] sumSq;
        long[] sumVal;
        long[] lazy;

        public SegmentTree(int n) {
            this.n = n;
            sumSq = new long[4 * n];
            sumVal = new long[4 * n];
            lazy = new long[4 * n];
        }

        private void apply(int node, int l, int r, long val) {
            // (c + val)^2 = c^2 + 2*val*c + val^2 * (r - l + 1)
            sumSq[node] = (sumSq[node] + 2 * val * sumVal[node] + val * val % MOD * (r - l + 1)) % MOD;
            sumVal[node] = (sumVal[node] + val * (r - l + 1)) % MOD;
            lazy[node] = (lazy[node] + val) % MOD;
        }

        private void pushDown(int node, int l, int r) {
            if (lazy[node] != 0) {
                int mid = l + (r - l) / 2;
                apply(2 * node, l, mid, lazy[node]);
                apply(2 * node + 1, mid + 1, r, lazy[node]);
                lazy[node] = 0;
            }
        }

        private void pushUp(int node) {
            sumSq[node] = (sumSq[2 * node] + sumSq[2 * node + 1]) % MOD;
            sumVal[node] = (sumVal[2 * node] + sumVal[2 * node + 1]) % MOD;
        }

        public void update(int node, int l, int r, int ql, int qr, long val) {
            if (ql <= l && r <= qr) {
                apply(node, l, r, val);
                return;
            }
            pushDown(node, l, r);
            int mid = l + (r - l) / 2;
            if (ql <= mid) {
                update(2 * node, l, mid, ql, qr, val);
            }
            if (qr > mid) {
                update(2 * node + 1, mid + 1, r, ql, qr, val);
            }
            pushUp(node);
        }

        public long query() {
            return sumSq[1];
        }
    }

    public int sumCounts(int[] nums) {
        int n = nums.length;
        SegmentTree tree = new SegmentTree(n);
        Map<Integer, Integer> lastSeen = new HashMap<>();
        long totalSum = 0;

        for (int r = 0; r < n; r++) {
            int l = lastSeen.getOrDefault(nums[r], -1) + 1;
            tree.update(1, 0, n - 1, l, r, 1);
            lastSeen.put(nums[r], r);
            totalSum = (totalSum + tree.query()) % MOD;
        }

        return (int) totalSum;
    }
}
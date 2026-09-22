class Solution {

    static class Node {
        int[] remain;
        int prod;

        Node(int k) {
            this.remain = new int[k];
            this.prod = 1;
        }
    }

    static class SegmentTree {
        private int n;
        private int k;
        private Node[] tree;

        public SegmentTree(int[] nums, int k) {
            this.n = nums.length;
            this.k = k;
            this.tree = new Node[4 * n];
            build(nums, 0, 0, n - 1);
        }

        private Node merge(Node left, Node right) {
            Node node = new Node(k);
            node.prod = (left.prod * right.prod) % k;

            // Prefixes entirely within the left child
            for (int r = 0; r < k; r++) {
                node.remain[r] += left.remain[r];
            }

            // Prefixes extending into the right child
            for (int r = 0; r < k; r++) {
                int combinedRemainder = (r * left.prod) % k;
                node.remain[combinedRemainder] += right.remain[r];
            }

            return node;
        }

        private void build(int[] nums, int treeIndex, int lo, int hi) {
            if (lo == hi) {
                tree[treeIndex] = new Node(k);
                int val = nums[lo] % k;
                tree[treeIndex].remain[val] = 1;
                tree[treeIndex].prod = val;
                return;
            }
            int mid = lo + (hi - lo) / 2;
            build(nums, 2 * treeIndex + 1, lo, mid);
            build(nums, 2 * treeIndex + 2, mid + 1, hi);
            tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
        }

        public void update(int idx, int val) {
            update(0, 0, n - 1, idx, val % k);
        }

        private void update(int treeIndex, int lo, int hi, int idx, int val) {
            if (lo == hi) {
                tree[treeIndex] = new Node(k);
                tree[treeIndex].remain[val] = 1;
                tree[treeIndex].prod = val;
                return;
            }
            int mid = lo + (hi - lo) / 2;
            if (idx <= mid) {
                update(2 * treeIndex + 1, lo, mid, idx, val);
            } else {
                update(2 * treeIndex + 2, mid + 1, hi, idx, val);
            }
            tree[treeIndex] = merge(tree[2 * treeIndex + 1], tree[2 * treeIndex + 2]);
        }

        public Node query(int l, int r) {
            return query(0, 0, n - 1, l, r);
        }

        private Node query(int treeIndex, int lo, int hi, int l, int r) {
            if (l <= lo && hi <= r) {
                return tree[treeIndex];
            }
            int mid = lo + (hi - lo) / 2;
            if (r <= mid) {
                return query(2 * treeIndex + 1, lo, mid, l, r);
            }
            if (l > mid) {
                return query(2 * treeIndex + 2, mid + 1, hi, l, r);
            }

            Node left = query(2 * treeIndex + 1, lo, mid, l, r);
            Node right = query(2 * treeIndex + 2, mid + 1, hi, l, r);
            return merge(left, right);
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        int n = nums.length;
        SegmentTree segmentTree = new SegmentTree(nums, k);
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            // Perform persistent point update
            segmentTree.update(index, value);

            // Query the suffix subarray nums[start..n-1]
            Node resNode = segmentTree.query(start, n - 1);
            result[i] = resNode.remain[x];
        }

        return result;
    }
}
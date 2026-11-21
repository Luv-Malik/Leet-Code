import java.util.*;

class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        List<Integer>[] origTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origTree[parent[i]].add(i);
        }

        // Track the most recent ancestor on the current DFS path for each character ('a'-'z')
        int[] lastCharAncestor = new int[26];
        Arrays.fill(lastCharAncestor, -1);

        int[] newParent = Arrays.copyOf(parent, n);
        assignNewParents(0, s, origTree, lastCharAncestor, newParent);

        // Build the restructured tree using newParent
        List<Integer>[] updatedTree = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            updatedTree[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            updatedTree[newParent[i]].add(i);
        }

        int[] ans = new int[n];
        computeSubtreeSizes(0, updatedTree, ans);
        return ans;
    }

    private void assignNewParents(
            int node,
            String s,
            List<Integer>[] origTree,
            int[] lastCharAncestor,
            int[] newParent) {
        int charIdx = s.charAt(node) - 'a';
        int prevAncestor = lastCharAncestor[charIdx];

        if (prevAncestor != -1) {
            newParent[node] = prevAncestor;
        }

        // Set current node as the latest ancestor for its character while exploring its subtree
        lastCharAncestor[charIdx] = node;
        for (int child : origTree[node]) {
            assignNewParents(child, s, origTree, lastCharAncestor, newParent);
        }

        // Backtrack: restore previous ancestor for this character
        lastCharAncestor[charIdx] = prevAncestor;
    }

    private int computeSubtreeSizes(int node, List<Integer>[] updatedTree, int[] ans) {
        int size = 1;
        for (int child : updatedTree[node]) {
            size += computeSubtreeSizes(child, updatedTree, ans);
        }
        ans[node] = size;
        return size;
    }
}
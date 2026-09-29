public class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        
        // A valid path length is m + n - 1. If it's odd, it cannot be balanced.
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        
        // Starting or ending characters must allow a valid parentheses structure
        if (grid[0][0] == ')' || grid[m - 1][n - 1] == '(') {
            return false;
        }
        
        // Maximum possible open balance along any valid path
        int maxOpen = (m + n - 1) / 2;
        
        // memo[r][c][k] stores whether a valid path to the end exists 
        // starting from (r, c) with a current open balance of k.
        // 0 = unvisited, 1 = true, 2 = false
        byte[][][] memo = new byte[m][n][maxOpen + 1];
        
        return dfs(grid, 0, 0, 0, memo, maxOpen);
    }

    private boolean dfs(char[][] grid, int r, int c, int open, byte[][][] memo, int maxOpen) {
        // Update balance based on current cell
        if (grid[r][c] == '(') {
            open++;
        } else {
            open--;
        }

        // If open balance goes negative, or exceeds maximum allowable open brackets
        if (open < 0 || open > maxOpen) {
            return false;
        }

        int m = grid.length;
        int n = grid[0].length;

        // Base case: Reached destination cell
        if (r == m - 1 && c == n - 1) {
            return open == 0;
        }

        // Check memoization table
        if (memo[r][c][open] != 0) {
            return memo[r][c][open] == 1;
        }

        boolean found = false;

        // Move Down
        if (r + 1 < m) {
            found = dfs(grid, r + 1, c, open, memo, maxOpen);
        }

        // Move Right
        if (!found && c + 1 < n) {
            found = dfs(grid, r, c + 1, open, memo, maxOpen);
        }

        // Store result in memoization table
        memo[r][c][open] = (byte) (found ? 1 : 2);
        
        return found;
    }
}
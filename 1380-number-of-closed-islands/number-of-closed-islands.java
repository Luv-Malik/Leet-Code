class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Step 1: Turn all '0's connected to the boundary into '1's
        // Check top and bottom boundaries
        for (int j = 0; j < cols; j++) {
            if (grid[0][j] == 0) {
                dfs(grid, 0, j);
            }
            if (grid[rows - 1][j] == 0) {
                dfs(grid, rows - 1, j);
            }
        }
        
        // Check left and right boundaries
        for (int i = 0; i < rows; i++) {
            if (grid[i][0] == 0) {
                dfs(grid, i, 0);
            }
            if (grid[i][cols - 1] == 0) {
                dfs(grid, i, cols - 1);
            }
        }
        
        // Step 2: Count the remaining closed islands in the inner grid
        int closedIslandsCount = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == 0) {
                    closedIslandsCount++;
                    dfs(grid, i, j); // Sink the island so we don't count it again
                }
            }
        }
        
        return closedIslandsCount;
    }
    
    private void dfs(int[][] grid, int r, int c) {
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Base case: out of bounds or already water/visited
        if (r < 0 || c < 0 || r >= rows || c >= cols || grid[r][c] == 1) {
            return;
        }
        
        // Mark current cell as visited by changing it to water (1)
        grid[r][c] = 1;
        
        // Recursively visit all 4 adjacent directions
        dfs(grid, r + 1, c); // Down
        dfs(grid, r - 1, c); // Up
        dfs(grid, r, c + 1); // Right
        dfs(grid, r, c - 1); // Left
    }
}
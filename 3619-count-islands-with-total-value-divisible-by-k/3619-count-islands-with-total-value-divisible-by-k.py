class Solution:
    def countIslands(self, grid: list[list[int]], k: int) -> int:
        m, n = len(grid), len(grid[0])
        count = 0
        
        def dfs(r: int, c: int) -> int:
            # Mark the current cell as visited by setting it to 0
            total = grid[r][c]
            grid[r][c] = 0
            
            # Explore 4 directions
            for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                nr, nc = r + dr, c + dc
                if 0 <= nr < m and 0 <= nc < n and grid[nr][nc] > 0:
                    total += dfs(nr, nc)
                    
            return total

        for i in range(m):
            for j in range(n):
                if grid[i][j] > 0:
                    # Found an island, calculate its total value
                    island_sum = dfs(i, j)
                    if island_sum % k == 0:
                        count += 1
                        
        return count
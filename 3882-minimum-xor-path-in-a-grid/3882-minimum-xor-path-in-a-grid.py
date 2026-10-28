class Solution:
    def minCost(self, grid: list[list[int]]) -> int:
        m, n = len(grid), len(grid[0])
        
        # dp[j] will store the set of possible XOR values for the current row at column j
        # To optimize space, we can use an array of sets or boolean arrays of size 1024.
        
        # Using a 2D array of sets for clarity: dp[i][j] = set of reachable XORs at (i, j)
        # Given m * n <= 1000, we can optimize space to O(n * 1024)
        
        dp = [set() for _ in range(n)]
        
        # Initialize the starting cell (0, 0)
        curr_xor = 0
        # Wait, let's process row by row properly:
        prev_row = [set() for _ in range(n)]
        
        for i in range(m):
            curr_row = [set() for _ in range(n)]
            for j in range(n):
                val = grid[i][j]
                if i == 0 and j == 0:
                    curr_row[j].add(val)
                else:
                    if i > 0:
                        for x in prev_row[j]:
                            curr_row[j].add(x ^ val)
                    if j > 0:
                        for x in curr_row[j - 1]:
                            curr_row[j].add(x ^ val)
            prev_row = curr_row
            
        return min(prev_row[-1])
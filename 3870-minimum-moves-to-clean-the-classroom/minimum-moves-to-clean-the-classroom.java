import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class Solution {
    public int minMoves(String[] classroom, int maxEnergy) {
        int m = classroom.length;
        int n = classroom[0].length();

        int startR = -1, startC = -1;
        int litterCount = 0;
        int[][] litterMap = new int[m][n];
        
        // Initialize mapping table with -1
        for (int i = 0; i < m; i++) {
            Arrays.fill(litterMap[i], -1);
        }

        // 1. Locate start ('S') and assign indices to each litter ('L')
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startR = r;
                    startC = c;
                } else if (ch == 'L') {
                    litterMap[r][c] = litterCount++;
                }
            }
        }

        // If there are no litter items to collect
        if (litterCount == 0) {
            return 0;
        }

        int targetMask = (1 << litterCount) - 1;

        // 2. Visited state tracker: vis[row][col][energy][mask]
        boolean[][][][] visited = new boolean[m][n][maxEnergy + 1][1 << litterCount];

        // Queue storing states: [r, c, energy, mask]
        Queue<int[]> queue = new ArrayDeque<>();
        
        // Push initial state
        queue.offer(new int[]{startR, startC, maxEnergy, 0});
        visited[startR][startC][maxEnergy][0] = true;

        int moves = 0;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

        // 3. Breadth-First Search
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int e = curr[2];
                int mask = curr[3];

                // If all litter items are collected
                if (mask == targetMask) {
                    return moves;
                }

                // If energy is depleted, we cannot make a move from here
                if (e == 0) {
                    continue;
                }

                for (int[] dir : directions) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Out of bounds or obstacle cell 'X'
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    char cellType = classroom[nr].charAt(nc);
                    int nextMask = mask;

                    // Collect litter if on an 'L' cell
                    if (cellType == 'L' && litterMap[nr][nc] != -1) {
                        nextMask |= (1 << litterMap[nr][nc]);
                    }

                    // Reset energy if on an 'R' cell, otherwise decrease by 1
                    int nextEnergy = (cellType == 'R') ? maxEnergy : e - 1;

                    // Add to queue if this specific state hasn't been visited yet
                    if (!visited[nr][nc][nextEnergy][nextMask]) {
                        visited[nr][nc][nextEnergy][nextMask] = true;
                        queue.offer(new int[]{nr, nc, nextEnergy, nextMask});
                    }
                }
            }
            moves++;
        }

        return -1;
    }
}
import java.util.Arrays;

public class Solution {
    public int minimumChanges(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        
        int INF = 1000000;
        
        // cost[i][j] stores the minimum changes to make substring s[i...j] a semi-palindrome
        int[][] cost = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                cost[i][j] = getMinChanges(chars, i, j, INF);
            }
        }
        
        // dp[i][j] stores the minimum changes to partition the prefix of length i into j parts
        int[][] dp = new int[n + 1][k + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }
        dp[0][0] = 0;
        
        for (int j = 1; j <= k; j++) {
            for (int i = 1; i <= n; i++) {
                for (int h = j - 1; h < i; h++) {
                    if (dp[h][j - 1] != INF && cost[h][i - 1] != INF) {
                        dp[i][j] = Math.min(dp[i][j], dp[h][j - 1] + cost[h][i - 1]);
                    }
                }
            }
        }
        
        return dp[n][k];
    }
    
    private int getMinChanges(char[] chars, int start, int end, int INF) {
        int len = end - start + 1;
        int minChanges = INF;
        
        // Check all proper divisors d of the length (1 <= d < len)
        for (int d = 1; d < len; d++) {
            if (len % d == 0) {
                int currentChanges = 0;
                // Check each group of divisor d
                for (int offset = 0; offset < d; offset++) {
                    int l = start + offset;
                    int r = start + len - d + offset;
                    while (l < r) {
                        if (chars[l] != chars[r]) {
                            currentChanges++;
                        }
                        l += d;
                        r -= d;
                    }
                }
                minChanges = Math.min(minChanges, currentChanges);
            }
        }
        return minChanges;
    }
}
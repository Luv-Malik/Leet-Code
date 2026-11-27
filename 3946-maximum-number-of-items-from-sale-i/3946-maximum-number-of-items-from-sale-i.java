class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;
        int[] free = new int[n];

        for (int i = 0; i < n; i++) {
            int factorI = items[i][0];
            int count = 0;
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % factorI == 0) {
                    count++;
                }
            }
            free[i] = count;
        }

        int[] dp = new int[budget + 1];

        for (int i = 0; i < n; i++) {
            int price = items[i][1];
            int firstGain = 1 + free[i];

            // 0/1 step: first copy grants free copies once
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(dp[b], dp[b - price] + firstGain);
            }

            // Unbounded step: extra copies grant 1 copy each
            for (int b = price; b <= budget; b++) {
                dp[b] = Math.max(dp[b], dp[b - price] + 1);
            }
        }

        return dp[budget];
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {
    private static final int MOD = 1_000_000_007;
    private long[][] comb;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        
        // Step 1: Precompute binomial coefficients (Pascal's Triangle)
        comb = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            comb[i][0] = 1;
            for (int j = 1; j <= i; j++) {
                comb[i][j] = (comb[i - 1][j - 1] + comb[i - 1][j]) % MOD;
            }
        }

        List<Integer> list = Arrays.stream(nums).boxed().collect(Collectors.toList());
        
        // Subtract 1 to exclude the original array order
        return (int) ((countWays(list) - 1 + MOD) % MOD);
    }

    private long countWays(List<Integer> nums) {
        if (nums.size() <= 2) {
            return 1;
        }

        int root = nums.get(0);
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        // Partition elements into left and right subtrees
        for (int i = 1; i < nums.size(); i++) {
            int val = nums.get(i);
            if (val < root) {
                left.add(val);
            } else {
                right.add(val);
            }
        }

        long leftWays = countWays(left);
        long rightWays = countWays(right);

        int L = left.size();
        int R = right.size();

        // Ways to interleave left and right subtrees: C(L + R, L)
        long ways = comb[L + R][L];
        
        // Multiply by the number of valid arrangements within left and right subtrees
        ways = (ways * leftWays) % MOD;
        ways = (ways * rightWays) % MOD;

        return ways;
    }
}
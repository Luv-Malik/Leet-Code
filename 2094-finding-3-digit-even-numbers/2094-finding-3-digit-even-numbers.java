import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] findEvenNumbers(int[] digits) {
        // Count the frequency of each available digit (0-9)
        int[] available = new int[10];
        for (int d : digits) {
            available[d]++;
        }

        List<Integer> result = new ArrayList<>();

        // Iterate through all 3-digit even numbers in ascending order
        for (int num = 100; num < 1000; num += 2) {
            int hundreds = num / 100;
            int tens = (num / 10) % 10;
            int ones = num % 10;

            // Temporarily use the digits
            available[hundreds]--;
            available[tens]--;
            available[ones]--;

            // If all counts remain >= 0, we had enough digits to form this number
            if (available[hundreds] >= 0 && available[tens] >= 0 && available[ones] >= 0) {
                result.add(num);
            }

            // Restore the counts for the next iteration
            available[hundreds]++;
            available[tens]++;
            available[ones]++;
        }

        // Convert the list to the required int[] array
        int[] ans = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            ans[i] = result.get(i);
        }
        return ans;
    }
}

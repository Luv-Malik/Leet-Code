import java.util.HashSet;
import java.util.Set;

public class Solution {
    public int totalNumbers(int[] digits) {
        int[] freq = new int[10];
        for (int d : digits) {
            freq[d]++;
        }

        int count = 0;

        // Iterate over all possible 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            int d1 = num / 100;        // Hundreds place
            int d2 = (num / 10) % 10;  // Tens place
            int d3 = num % 10;         // Units place

            int[] req = new int[10];
            req[d1]++;
            req[d2]++;
            req[d3]++;

            boolean valid = true;
            for (int d = 0; d < 10; d++) {
                if (req[d] > freq[d]) {
                    valid = false;
                    break;
                }
            }

            if (valid) {
                count++;
            }
        }

        return count;
    }
}
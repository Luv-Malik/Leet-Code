class Solution {
    public long countCommas(long n) {
        long totalCommas = 0;
        long threshold = 1000L; // 10^3 (1st comma threshold)
        
        while (n >= threshold) {
            // Count all numbers in the range [threshold, n]
            totalCommas += (n - threshold + 1);
            
            // Prevent 64-bit signed integer overflow (Long.MAX_VALUE)
            if (threshold > Long.MAX_VALUE / 1000) {
                break;
            }
            
            threshold *= 1000;
        }
        
        return totalCommas;
    }
}
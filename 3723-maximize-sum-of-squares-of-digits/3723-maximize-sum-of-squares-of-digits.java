class Solution {
    public String maxSumOfSquares(int num, int sum) {
        // If the required sum is greater than the maximum possible sum (9 * num), return ""
        if ((long) num * 9 < sum) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < num; i++) {
            if (sum >= 9) {
                sb.append('9');
                sum -= 9;
            } else {
                sb.append((char) ('0' + sum));
                sum = 0;
            }
        }
        
        return sb.toString();
    }
}
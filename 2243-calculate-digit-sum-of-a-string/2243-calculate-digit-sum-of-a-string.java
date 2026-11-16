class Solution {
    public String digitSum(String s, int k) {
        // Continue rounds until the string length is less than or equal to k
        while (s.length() > k) {
            StringBuilder nextRound = new StringBuilder();
            
            // Process the string in chunks of size k
            for (int i = 0; i < s.length(); i += k) {
                int groupSum = 0;
                
                // Sum the digits of the current group
                int end = Math.min(s.length(), i + k);
                for (int j = i; j < end; j++) {
                    groupSum += s.charAt(j) - '0';
                }
                
                // Append the sum string to the new round result
                nextRound.append(groupSum);
            }
            
            // Update s for the next round
            s = nextRound.toString();
        }
        
        return s;
    }
}
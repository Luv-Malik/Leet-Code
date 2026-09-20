class Solution {
    public int reverseDegree(String s) {
        int totalSum = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Calculate 1-based reversed alphabet position ('a' -> 26, 'b' -> 25, ..., 'z' -> 1)
            int reversePos = 26 - (s.charAt(i) - 'a');
            
            // Calculate 1-based string index position
            int stringIndex = i + 1;
            
            // Add product to running sum
            totalSum += reversePos * stringIndex;
        }
        
        return totalSum;
    }
}
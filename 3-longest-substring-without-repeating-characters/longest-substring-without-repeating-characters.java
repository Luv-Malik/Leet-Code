import java.util.HashMap;
import java.util.Map;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        // Map to store character -> its last seen index
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0;
        int maxLen = 0;

        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // If the character is in the map and within the current window
            if (charMap.containsKey(currentChar) && charMap.get(currentChar) >= left) {
                left = charMap.get(currentChar) + 1;
            }

            // Update the character's last seen index
            charMap.put(currentChar, right);

            // Calculate window length and update maximum
            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}

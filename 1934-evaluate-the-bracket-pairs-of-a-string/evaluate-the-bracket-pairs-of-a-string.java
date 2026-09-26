import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        // Step 1: Populate the map for O(1) key lookups
        Map<String, String> lookup = new HashMap<>();
        for (List<String> pair : knowledge) {
            lookup.put(pair.get(0), pair.get(1));
        }

        StringBuilder result = new StringBuilder();
        StringBuilder keyBuffer = new StringBuilder();
        boolean inBracket = false;

        // Step 2: Iterate through the string character by character
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == '(') {
                inBracket = true;
            } else if (ch == ')') {
                inBracket = false;
                String key = keyBuffer.toString();
                result.append(lookup.getOrDefault(key, "?"));
                keyBuffer.setLength(0); // Clear key buffer for next key
            } else if (inBracket) {
                keyBuffer.append(ch);
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }
}
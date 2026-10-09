class Solution {
    public int minInsertions(String s) {
        int insertions = 0;
        int neededRight = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // If neededRight is odd, we have a single unmatched ')' before this '('
                if (neededRight % 2 != 0) {
                    insertions++;   // Insert 1 ')' to complete the pair
                    neededRight--;  // Adjust balance back to an even state
                }
                neededRight += 2;   // Each '(' expects two ')'
            } else { // c == ')'
                neededRight--;
                // If neededRight falls below 0, we found a ')' without a matching '('
                if (neededRight < 0) {
                    insertions++;   // Insert 1 '('
                    neededRight += 2; // The inserted '(' expects 2 ')', but we already have 1
                }
            }
        }

        // Add any remaining required ')' at the end
        return insertions + neededRight;
    }
}
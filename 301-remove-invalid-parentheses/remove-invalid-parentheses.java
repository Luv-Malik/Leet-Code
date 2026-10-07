import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftRem = 0;
        int rightRem = 0;

        // Step 1: Count minimum misplaced '(' and ')'
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                leftRem++;
            } else if (c == ')') {
                if (leftRem > 0) {
                    leftRem--; // Valid pair found
                } else {
                    rightRem++; // Misplaced ')'
                }
            }
        }

        Set<String> result = new HashSet<>();
        backtrack(s, 0, 0, leftRem, rightRem, new StringBuilder(), result);
        return new ArrayList<>(result);
    }

    private void backtrack(String s, int index, int balance, int leftRem, int rightRem, 
                           StringBuilder current, Set<String> result) {
        
        // Pruning: Invalid state if open/close balance goes below zero or remaining counts drop below zero
        if (balance < 0 || leftRem < 0 || rightRem < 0) {
            return;
        }

        // Base case: Reached the end of the string
        if (index == s.length()) {
            if (leftRem == 0 && rightRem == 0 && balance == 0) {
                result.add(current.toString());
            }
            return;
        }

        char currentChar = s.charAt(index);
        int len = current.length();

        // Option 1: Ignore/Remove the current character (if it is an unmatched parenthesis)
        if (currentChar == '(' && leftRem > 0) {
            backtrack(s, index + 1, balance, leftRem - 1, rightRem, current, result);
        } else if (currentChar == ')' && rightRem > 0) {
            backtrack(s, index + 1, balance, leftRem, rightRem - 1, current, result);
        }

        // Option 2: Keep the current character
        current.append(currentChar);
        int nextBalance = balance;
        if (currentChar == '(') {
            nextBalance++;
        } else if (currentChar == ')') {
            nextBalance--;
        }

        backtrack(s, index + 1, nextBalance, leftRem, rightRem, current, result);

        // Backtrack (undo addition)
        current.setLength(len);
    }
}
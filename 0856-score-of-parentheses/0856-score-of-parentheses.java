import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public int scoreOfParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(0); // Base score for the outermost level

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0); // Start a new nested level
            } else {
                int v = stack.pop(); // Inner level score
                int innerScore = Math.max(2 * v, 1); // 1 for "()", 2 * v for "(A)"
                stack.push(stack.pop() + innerScore); // Add to current outer level
            }
        }

        return stack.peek();
    }
}
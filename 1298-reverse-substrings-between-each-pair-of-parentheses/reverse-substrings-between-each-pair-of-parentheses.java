import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String reverseParentheses(String s) {
        int n = s.length();
        int[] pair = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        // Step 1: Pair up opening and closing brackets
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                int openIndex = stack.pop();
                pair[openIndex] = i;
                pair[i] = openIndex;
            }
        }

        // Step 2: Traverse string, bouncing across bracket pairs
        StringBuilder sb = new StringBuilder();
        int curr = 0;
        int direction = 1; // 1 for right, -1 for left

        while (curr < n) {
            char c = s.charAt(curr);
            if (c == '(' || c == ')') {
                curr = pair[curr];  // Jump to corresponding parenthesis
                direction = -direction; // Reverse direction
            } else {
                sb.append(c);
            }
            curr += direction;
        }

        return sb.toString();
    }
}
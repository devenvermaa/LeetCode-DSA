import java.util.Stack;

public class Solution {
    public String decodeString(String s) {
        Stack<Integer> countStack = new Stack<>();
        Stack<StringBuilder> stringStack = new Stack<>();
        StringBuilder currentString = new StringBuilder();
        int k = 0;

        for (char ch : s.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Formulate the multi-digit repeat count (e.g., "100")
                k = k * 10 + (ch - '0');
            } else if (ch == '[') {
                // Push the current multiplier and the string built so far onto their stacks
                countStack.push(k);
                stringStack.push(currentString);
                
                // Reset for the inner string context
                currentString = new StringBuilder();
                k = 0;
            } else if (ch == ']') {
                // Decode the current segment
                StringBuilder decodedString = stringStack.pop();
                int repeatTimes = countStack.pop();
                
                // Append the current string segment 'repeatTimes' times
                for (int i = 0; i < repeatTimes; i++) {
                    decodedString.append(currentString);
                }
                // The outer context now absorbs this newly decoded part
                currentString = decodedString;
            } else {
                // It's a normal character, append it to the current running string
                currentString.append(ch);
            }
        }

        return currentString.toString();
    }
}
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(s.toCharArray(), 0, result);
        return result;
    }
    
    private void backtrack(char[] chars, int index, List<String> result) {
        // Base case: if we have processed all characters, add the current combination
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }
        
        // If it's a letter, we branch into two choices (lowercase and uppercase)
        if (Character.isLetter(chars[index])) {
            // Choice 1: Lowercase
            chars[index] = Character.toLowerCase(chars[index]);
            backtrack(chars, index + 1, result);
            
            // Choice 2: Uppercase
            chars[index] = Character.toUpperCase(chars[index]);
            backtrack(chars, index + 1, result);
        } else {
            // If it's a digit, just move to the next index
            backtrack(chars, index + 1, result);
        }
    }
}
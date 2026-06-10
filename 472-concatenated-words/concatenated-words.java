import java.util.*;

class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        
        for (String word : words) {
            // An empty string cannot be formed by at least two shorter words
            if (word.isEmpty()) continue; 
            
            // Temporarily remove the word so it doesn't match itself
            wordSet.remove(word);
            
            if (canForm(word, wordSet)) {
                result.add(word);
            }
            
            // Add it back for subsequent words to use
            wordSet.add(word);
        }
        
        return result;
    }
    
    private boolean canForm(String word, Set<String> wordSet) {
        int n = word.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Base case: empty string prefix is valid
        
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                // If the prefix up to j is valid and the substring from j to i exists in the set
                if (dp[j] && wordSet.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break; // Found a valid split for length i, move to next i
                }
            }
        }
        
        return dp[n];
    }
}
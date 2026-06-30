class Solution {
    public int numberOfSubstrings(String s) {
        // Array to store the last seen positions of 'a', 'b', and 'c'
        // Initialized to -1 because we haven't seen any characters yet
        int[] lastSeen = {-1, -1, -1};
        int count = 0;
        
        for (int i = 0; i < s.length(); i++) {
            // Update the last seen position of the current character
            lastSeen[s.charAt(i) - 'a'] = i;
            
            // If all three characters have been seen at least once
            if (lastSeen[0] != -1 && lastSeen[1] != -1 && lastSeen[2] != -1) {
                // Find the minimum index among the three characters
                int minIndex = Math.min(lastSeen[0], Math.min(lastSeen[1], lastSeen[2]));
                // All substrings starting from index 0 up to minIndex are valid
                count += minIndex + 1;
            }
        }
        
        return count;
    }
}
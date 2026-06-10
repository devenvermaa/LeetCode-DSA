public class Solution {
    public int longestSubstring(String s, int k) {
        return helper(s, 0, s.length(), k);
    }

    private int helper(String s, int start, int end, int k) {
        // Base case: If the segment is shorter than k, it cannot be valid
        if (end - start < k) {
            return 0;
        }

        // Count frequencies of each character in the current substring range
        int[] count = new int[26];
        for (int i = start; i < end; i++) {
            count[s.charAt(i) - 'a']++;
        }

        // Search for any character that breaks the constraint
        for (int i = start; i < end; i++) {
            if (count[s.charAt(i) - 'a'] < k) {
                // Find the next valid segment start
                int nextStart = i + 1;
                while (nextStart < end && count[s.charAt(nextStart) - 'a'] < k) {
                    nextStart++;
                }
                
                // Divide and conquer
                return Math.max(helper(s, start, i, k), helper(s, nextStart, end, k));
            }
        }

        // If no invalid characters were found, the entire substring is valid
        return end - start;
    }
}
class Solution {
    public String customSortString(String order, String s) {
        // Frequency array to count occurrences of each character in 's'
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }
        
        StringBuilder result = new StringBuilder();
        
        // Step 1: Append characters in the order dictated by 'order'
        for (char c : order.toCharArray()) {
            while (count[c - 'a'] > 0) {
                result.append(c);
                count[c - 'a']--;
            }
        }
        
        // Step 2: Append the remaining characters that were not in 'order'
        for (int i = 0; i < 26; i++) {
            while (count[i] > 0) {
                result.append((char) (i + 'a'));
                count[i]--;
            }
        }
        
        return result.toString();
    }
}
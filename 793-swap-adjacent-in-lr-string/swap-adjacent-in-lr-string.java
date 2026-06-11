class Solution {
    public boolean canTransform(String start, String result) {
        // If lengths don't match, transformation is impossible
        if (start.length() != result.length()) {
            return false;
        }
        
        int n = start.length();
        int i = 0; // Pointer for 'start'
        int j = 0; // Pointer for 'result'
        
        while (i < n || j < n) {
            // Move pointer i to the next non-'X' character in 'start'
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            // Move pointer j to the next non-'X' character in 'result'
            while (j < n && result.charAt(j) == 'X') {
                j++;
            }
            
            // If both pointers reached the end, strings are successfully matched
            if (i == n && j == n) {
                return true;
            }
            
            // If only one pointer reached the end, the relative structure doesn't match
            if (i == n || j == n) {
                return false;
            }
            
            // The actual characters (L or R) must match exactly in their sequence
            if (start.charAt(i) != result.charAt(j)) {
                return false;
            }
            
            // 'L' can only move left: its index in 'start' (i) must be >= its index in 'result' (j)
            if (start.charAt(i) == 'L' && i < j) {
                return false;
            }
            
            // 'R' can only move right: its index in 'start' (i) must be <= its index in 'result' (j)
            if (start.charAt(i) == 'R' && i > j) {
                return false;
            }
            
            // Move both pointers forward to check the next characters
            i++;
            j++;
        }
        
        return true;
    }
}
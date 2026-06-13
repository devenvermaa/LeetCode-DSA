class Solution {
    public int repeatedStringMatch(String a, String b) {
        StringBuilder sb = new StringBuilder(a);
        int count = 1;
        
        // Repeat 'a' until its length is at least the length of 'b'
        while (sb.length() < b.length()) {
            sb.append(a);
            count++;
        }
        
        // Check if b is a substring now
        if (sb.indexOf(b) != -1) return count;
        
        // Append one more time to handle wrapping variations
        sb.append(a);
        if (sb.indexOf(b) != -1) return count + 1;
        
        return -1;
    }
}
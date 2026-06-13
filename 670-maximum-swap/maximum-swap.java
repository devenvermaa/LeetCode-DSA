class Solution {
    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();
        int[] lastIndex = new int[10];
        
        // Record the last occurrence of each digit
        for (int i = 0; i < digits.length; i++) {
            lastIndex[digits[i] - '0'] = i;
        }
        
        // Find the first digit that can be swapped with a larger digit appearing later
        for (int i = 0; i < digits.length; i++) {
            for (int d = 9; d > digits[i] - '0'; d--) {
                if (lastIndex[d] > i) {
                    // Perform the swap
                    char temp = digits[i];
                    digits[i] = digits[lastIndex[d]];
                    digits[lastIndex[d]] = temp;
                    
                    return Integer.parseInt(new String(digits));
                }
            }
        }
        
        return num; // Return original if no beneficial swap is found
    }
}
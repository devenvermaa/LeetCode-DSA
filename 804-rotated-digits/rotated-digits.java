class Solution {
    public int rotatedDigits(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (isValid(i)) {
                count++;
            }
        }
        return count;
    }
    
    private boolean isValid(int num) {
        boolean hasValidChangeDigit = false;
        
        while (num > 0) {
            int digit = num % 10;
            
            // If it contains 3, 4, or 7, it's immediately invalid
            if (digit == 3 || digit == 4 || digit == 7) {
                return false;
            }
            
            // Track if it contains at least one digit that morphs into another valid digit
            if (digit == 2 || digit == 5 || digit == 6 || digit == 9) {
                hasValidChangeDigit = true;
            }
            
            num /= 10;
        }
        
        return hasValidChangeDigit;
    }
}
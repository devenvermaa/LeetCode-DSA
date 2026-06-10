public class Solution {
    public int integerReplacement(int n) {
        long num = n; // Use long to prevent overflow during (num + 1)
        int count = 0;
        
        while (num > 1) {
            if ((num & 1) == 0) {
                // If even, always divide by 2
                num >>>= 1;
            } else {
                // If odd, look at the last two bits
                // If it ends in 3 (binary 11) and isn't the number 3 itself, increment
                if ((num & 3) == 3 && num != 3) {
                    num++;
                } else {
                    // Otherwise (ends in 01, or is the number 3), decrement
                    num--;
                }
            }
            count++;
        }
        
        return count;
    }
}
class Solution {
    public int magicalString(int n) {
        // Base cases
        if (n <= 0) return 0;
        if (n <= 3) return 1; // For n=1,2,3 the string is "122", which has exactly one '1'
        
        // Array to simulate the magical string up to size n
        int[] s = new int[n + 1];
        
        // Seed the initial sequence "122"
        s[0] = 1;
        s[1] = 2;
        s[2] = 2;
        
        int head = 2; // Dictates the count of the next group
        int tail = 3; // Dictates where the next group is written
        int numOnes = 1; // We already have one '1' from our seed
        
        while (tail < n) {
            // Determine the next number to append (alternate between 1 and 2)
            int nextNum = 3 - s[tail - 1];
            
            // Look at s[head] to know how many times to write nextNum
            int count = s[head];
            
            for (int i = 0; i < count && tail < n; i++) {
                s[tail] = nextNum;
                if (nextNum == 1) {
                    numOnes++;
                }
                tail++;
            }
            head++; // Move head to read the size of the next group
        }
        
        return numOnes;
    }
}
class Solution {
    public int numberOfWays(String corridor) {
        long totalWays = 1;
        int numSeats = 0;
        int lastSeatIndex = -1;
        int MOD = 1_000_000_007;

        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                numSeats++;
                
                // If this is the start of a new pair (the 3rd, 5th, 7th... seat found)
                // and it's not the very first seat section.
                if (numSeats > 2 && numSeats % 2 == 1) {
                    // Number of choices = current index - last seat's index
                    long choices = i - lastSeatIndex;
                    totalWays = (totalWays * choices) % MOD;
                }
                
                // Keep track of the index of the most recent seat
                lastSeatIndex = i;
            }
        }

        // If there are no seats, or the total number of seats is odd, division is impossible.
        if (numSeats == 0 || numSeats % 2 != 0) {
            return 0;
        }

        return (int) totalWays;
    }
}
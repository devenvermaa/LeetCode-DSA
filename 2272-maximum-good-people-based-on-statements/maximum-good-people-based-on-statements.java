class Solution {
    public int maximumGood(int[][] statements) {
        int n = statements.length;
        int maxGood = 0;
        int totalStates = 1 << n; // 2^n combinations

        for (int mask = 0; mask < totalStates; mask++) {
            if (isValid(mask, statements, n)) {
                maxGood = Math.max(maxGood, Integer.bitCount(mask));
            }
        }
        return maxGood;
    }

    private boolean isValid(int mask, int[][] statements, int n) {
        for (int i = 0; i < n; i++) {
            // If person i is designated as "bad", their statements don't matter
            if (((mask >> i) & 1) == 0) continue;

            // If person i is "good", verify all of their statements
            for (int j = 0; j < n; j++) {
                if (statements[i][j] == 2) continue; // No statement made

                int actualStatusOfJ = (mask >> j) & 1;
                // If statement says good (1) but mask says bad (0), or vice versa
                if (statements[i][j] != actualStatusOfJ) {
                    return false; 
                }
            }
        }
        return true;
    }
}
import java.util.Arrays;

public class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) {
            return m;
        }

        int size = 2 * m;
        long[][] T = new long[size][size];

        // Build the transition matrix T
        // State index 0 to m-1: 'up' states for values 0 to m-1
        // State index m to 2m-1: 'down' states for values 0 to m-1
        for (int x = 0; x < m; x++) {
            // From up[x], we can go to down[y] where y > x
            for (int y = x + 1; y < m; y++) {
                T[m + y][x] = 1;
            }
            // From down[x], we can go to up[y] where y < x
            for (int y = 0; y < x; y++) {
                T[y][m + x] = 1;
            }
        }

        // Compute T^(n-1)
        long[][] Fn = power(T, n - 1, size);

        // Initial vector has 1 for every valid starting state.
        // For an array of length >= 2, the first step can either go up or down.
        // So we can assume base states have 1 for both up and down transitions initially.
        long total = 0;
        for (int i = 0; i < size; i++) {
            long waysToStateI = 0;
            for (int j = 0; j < size; j++) {
                waysToStateI = (waysToStateI + Fn[i][j]) % MOD;
            }
            total = (total + waysToStateI) % MOD;
        }

        return (int) total;
    }

    // Matrix multiplication modulo 10^9 + 7
    private long[][] multiply(long[][] A, long[][] B, int size) {
        long[][] C = new long[size][size];
        for (int i = 0; i < size; i++) {
            for (int k = 0; k < size; k++) {
                if (A[i][k] == 0) continue;
                for (int j = 0; j < size; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    // Matrix exponentiation: binary exponentiation to compute A^p
    private long[][] power(long[][] A, int p, int size) {
        long[][] res = new long[size][size];
        for (int i = 0; i < size; i++) {
            res[i][i] = 1;
        }
        long[][] base = new long[size][size];
        for (int i = 0; i < size; i++) {
            base[i] = Arrays.copyOf(A[i], size);
        }

        while (p > 0) {
            if ((p & 1) == 1) {
                res = multiply(res, base, size);
            }
            base = multiply(base, base, size);
            p >>= 1;
        }
        return res;
    }
}
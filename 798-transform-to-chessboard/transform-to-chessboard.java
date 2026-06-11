class Solution {
    public int movesToChessboard(int[][] board) {
        int n = board.length;
        
        // 1. Check structural validity of the board
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                // For any four corners of a rectangle in the grid,
                // the XOR sum of their states must be 0.
                if ((board[0][0] ^ board[r][0] ^ board[0][c] ^ board[r][c]) != 0) {
                    return -1;
                }
            }
        }
        
        // 2. Check row and column item counts
        int rowSum = 0, colSum = 0;
        int rowMiss = 0, colMiss = 0;
        
        for (int i = 0; i < n; i++) {
            rowSum += board[0][i];
            colSum += board[i][0];
            
            // Count mismatches against an ideal chessboard pattern starting with 0 (0, 1, 0, 1...)
            if (board[0][i] != (i % 2)) rowMiss++;
            if (board[i][0] != (i % 2)) colMiss++;
        }
        
        // In a valid board, the count of 1s must be roughly n / 2
        if (rowSum < n / 2 || rowSum > (n + 1) / 2) return -1;
        if (colSum < n / 2 || colSum > (n + 1) / 2) return -1;
        
        // 3. Calculate minimum swaps needed
        int rowMoves = 0;
        int colMoves = 0;
        
        if (n % 2 == 1) {
            // If n is odd, we are forced into one specific pattern choice
            if (rowMiss % 2 == 1) rowMiss = n - rowMiss;
            if (colMiss % 2 == 1) colMiss = n - colMiss;
            rowMoves = rowMiss / 2;
            colMoves = colMiss / 2;
        } else {
            // If n is even, choose the minimum flips between starting with 0 vs starting with 1
            rowMoves = Math.min(rowMiss, n - rowMiss) / 2;
            colMoves = Math.min(colMiss, n - colMiss) / 2;
        }
        
        return rowMoves + colMoves;
    }
}
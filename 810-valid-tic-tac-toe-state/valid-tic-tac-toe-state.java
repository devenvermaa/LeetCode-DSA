class Solution {
    public boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;
        
        // 1. Count the number of X's and O's on the board
        for (String row : board) {
            for (char c : row.toCharArray()) {
                if (c == 'X') countX++;
                if (c == 'O') countO++;
            }
        }
        
        // 'X' goes first, so countX must be equal to countO or countO + 1
        if (countX != countO && countX != countO + 1) {
            return false;
        }
        
        // 2. Check if either player has won
        boolean xWins = isWinner(board, 'X');
        boolean oWins = isWinner(board, 'O');
        
        // Both players cannot win at the same time
        if (xWins && oWins) {
            return false;
        }
        
        // If X wins, X must have exactly 1 more piece than O
        if (xWins && countX != countO + 1) {
            return false;
        }
        
        // If O wins, O must have exactly the same number of pieces as X
        if (oWins && countX != countO) {
            return false;
        }
        
        return true;
    }
    
    // Helper method to check if a specific player has 3 in a row
    private boolean isWinner(String[] board, char p) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i].charAt(0) == p && board[i].charAt(1) == p && board[i].charAt(2) == p) {
                return true;
            }
        }
        
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (board[0].charAt(i) == p && board[1].charAt(i) == p && board[2].charAt(i) == p) {
                return true;
            }
        }
        
        // Check diagonals
        if (board[0].charAt(0) == p && board[1].charAt(1) == p && board[2].charAt(2) == p) {
            return true;
        }
        if (board[0].charAt(2) == p && board[1].charAt(1) == p && board[2].charAt(0) == p) {
            return true;
        }
        
        return false;
    }
}
class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        // Calculate your Manhattan distance from the starting point (0, 0) to the target
        int myDistance = Math.abs(target[0]) + Math.abs(target[1]);
        
        // Check every ghost's distance to the target
        for (int[] ghost : ghosts) {
            int ghostDistance = Math.abs(ghost[0] - target[0]) + Math.abs(ghost[1] - target[1]);
            
            // If any ghost can reach the target at the same time or earlier, you cannot escape
            if (ghostDistance <= myDistance) {
                return false;
            }
        }
        
        return true;
    }
}
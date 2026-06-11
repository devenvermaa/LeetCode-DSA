class Solution {
    public boolean reachingPoints(int sx, int sy, int tx, int ty) {
        // Work backward from (tx, ty) until we drop below the start coordinates
        while (tx >= sx && ty >= sy) {
            // If we hit the start points exactly, we're done
            if (tx == sx && ty == sy) {
                return true;
            }
            
            if (tx > ty) {
                // If ty matches sy, we can only subtract ty from tx.
                // We check if the remaining distance (tx - sx) is perfectly divisible by ty.
                if (ty == sy) {
                    return (tx - sx) % ty == 0;
                }
                // Otherwise, generic fast backward step using modulo
                tx %= ty;
            } else {
                // If tx matches sx, we can only subtract tx from ty.
                // We check if the remaining distance (ty - sy) is perfectly divisible by tx.
                if (tx == sx) {
                    return (ty - sy) % tx == 0;
                }
                // Otherwise, generic fast backward step using modulo
                ty %= tx;
            }
        }
        
        return false;
    }
}
import java.util.Arrays;

public class Solution {
    public int maxBuilding(int n, int[][] restrictions) {
        // 1. Add the boundary restrictions: building 1 (height 0) and building n (max height n-1)
        // We create a new list including these boundaries to simplify the logic.
        int m = restrictions.length;
        int[][] r = new int[m + 2][2];
        
        r[0] = new int[]{1, 0}; // Building 1 always has height 0
        for (int i = 0; i < m; i++) {
            r[i + 1] = restrictions[i];
        }
        r[m + 1] = new int[]{n, n - 1}; // Building n can at most be n-1 high starting from 1
        
        // 2. Sort the restrictions by building ID
        Arrays.sort(r, (a, b) -> Integer.compare(a[0], b[0]));
        
        int len = r.length;
        
        // 3. Left-to-Right Pass: Update constraints based on previous restrictions
        for (int i = 1; i < len; i++) {
            int idDiff = r[i][0] - r[i - 1][0];
            r[i][1] = Math.min(r[i][1], r[i - 1][1] + idDiff);
        }
        
        // 4. Right-to-Left Pass: Update constraints based on following restrictions
        for (int i = len - 2; i >= 0; i--) {
            int idDiff = r[i + 1][0] - r[i][0];
            r[i][1] = Math.min(r[i][1], r[i + 1][1] + idDiff);
        }
        
        // 5. Calculate the maximum height possible between each pair of adjacent restricted buildings
        int maxHeight = 0;
        for (int i = 0; i < len - 1; i++) {
            int id1 = r[i][0], h1 = r[i][1];
            int id2 = r[i + 1][0], h2 = r[i + 1][1];
            
            // The peak between two restricted buildings id1 and id2 can be calculated as:
            // peak = ((id2 - id1) + h1 + h2) / 2
            int peak = (id2 - id1 + h1 + h2) / 2;
            maxHeight = Math.max(maxHeight, peak);
        }
        
        return maxHeight;
    }
}
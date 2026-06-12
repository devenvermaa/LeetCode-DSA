class Solution {
    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int[] hitBricks(int[][] grid, int[][] hits) {
        int R = grid.length, C = grid[0].length;
        
        // 1. Mark all valid hits on the grid
        for (int[] hit : hits) {
            if (grid[hit[0]][hit[1]] == 1) {
                grid[hit[0]][hit[1]] = 2; // 2 means it was a brick that got hit
            }
        }
        
        // 2. Initialize DSU. Virtual roof node at index R * C
        int roof = R * C;
        DSU dsu = new DSU(R * C + 1);
        
        // 3. Union remaining stable bricks
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (grid[r][c] == 1) {
                    int idx = r * C + c;
                    if (r == 0) dsu.union(idx, roof);
                    for (int[] d : dirs) {
                        int nr = r + d[0], nc = c + d[1];
                        if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == 1) {
                            dsu.union(idx, nr * C + nc);
                        }
                    }
                }
            }
        }
        
        // 4. Reverse process the hits
        int[] res = new int[hits.length];
        for (int i = hits.length - 1; i >= 0; i--) {
            int r = hits[i][0], c = hits[i][1];
            if (grid[r][c] == 2) {
                grid[r][c] = 1; // Put the brick back
                int idx = r * C + c;
                int prevRoofSize = dsu.getSize(roof);
                
                if (r == 0) dsu.union(idx, roof);
                for (int[] d : dirs) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == 1) {
                        dsu.union(idx, nr * C + nc);
                    }
                }
                
                int currRoofSize = dsu.getSize(roof);
                if (dsu.find(idx) == dsu.find(roof)) {
                    res[i] = Math.max(0, currRoofSize - prevRoofSize - 1);
                }
            }
        }
        return res;
    }
    
    class DSU {
        int[] parent;
        int[] size;
        
        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]);
        }
        
        void union(int i, int j) {
            int rootI = find(i);
            int rootJ = find(j);
            if (rootI != rootJ) {
                parent[rootI] = rootJ;
                size[rootJ] += size[rootI];
            }
        }
        
        int getSize(int i) {
            return size[find(i)];
        }
    }
}
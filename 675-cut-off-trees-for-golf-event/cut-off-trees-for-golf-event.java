class Solution {
    int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int cutOffTree(List<List<Integer>> forest) {
        List<int[]> trees = new ArrayList<>();
        int m = forest.size(), n = forest.get(0).size();
        
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int h = forest.get(r).get(c);
                if (h > 1) trees.add(new int[]{h, r, c});
            }
        }
        
        trees.sort((a, b) -> Integer.compare(a[0], b[0]));
        
        int startR = 0, startC = 0, totalSteps = 0;
        for (int[] tree : trees) {
            int steps = bfs(forest, startR, startC, tree[1], tree[2], m, n);
            if (steps == -1) return -1;
            totalSteps += steps;
            startR = tree[1];
            startC = tree[2];
        }
        return totalSteps;
    }

    private int bfs(List<List<Integer>> forest, int sr, int sc, int tr, int tc, int m, int n) {
        if (sr == tr && sc == tc) return 0;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[m][n];
        
        queue.add(new int[]{sr, sc});
        visited[sr][sc] = true;
        int steps = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : dirs) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if (nr >= 0 && nr < m && nc >= 0 && nc < n && !visited[nr][nc] && forest.get(nr).get(nc) != 0) {
                        if (nr == tr && nc == tc) return steps;
                        visited[nr][nc] = true;
                        queue.add(new int[]{nr, nc});
                    }
                }
            }
        }
        return -1;
    }
}
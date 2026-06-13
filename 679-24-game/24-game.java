class Solution {
    public boolean judgePoint24(int[] cards) {
        List<Double> list = new ArrayList<>();
        for (int card : cards) list.add((double) card);
        return dfs(list);
    }
    
    private boolean dfs(List<Double> list) {
        if (list.size() == 1) {
            return Math.abs(list.get(0) - 24.0) < 0.001;
        }
        
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (i == j) continue;
                
                List<Double> next = new ArrayList<>();
                for (int k = 0; k < list.size(); k++) {
                    if (k != i && k != j) next.add(list.get(k));
                }
                
                for (double val : getOperations(list.get(i), list.get(j))) {
                    next.add(val);
                    if (dfs(next)) return true;
                    next.remove(next.size() - 1); // backtrack
                }
            }
        }
        return false;
    }
    
    private List<Double> getOperations(double a, double b) {
        List<Double> res = new ArrayList<>(Arrays.asList(a + b, a - b, b - a, a * b));
        if (Math.abs(b) > 0.001) res.add(a / b);
        if (Math.abs(a) > 0.001) res.add(b / a);
        return res;
    }
}
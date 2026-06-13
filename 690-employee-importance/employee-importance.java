class Solution {
    public int getImportance(List<Employee> employees, int id) {
        // Step 1: Map each employee ID to its respective Employee object for O(1) lookups
        Map<Integer, Employee> map = new HashMap<>();
        for (Employee emp : employees) {
            map.put(emp.id, emp);
        }
        
        // Step 2: Use a helper DFS function to calculate total importance
        return dfs(id, map);
    }

    private int dfs(int id, Map<Integer, Employee> map) {
        Employee emp = map.get(id);
        int totalImportance = emp.importance;
        
        // Recursively add the importance of all direct and indirect subordinates
        for (int subId : emp.subordinates) {
            totalImportance += dfs(subId, map);
        }
        
        return totalImportance;
    }
}
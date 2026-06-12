class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        
        // If it's a leaf node and its value is 0, prune it
        if (root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        
        return root;
    }
}
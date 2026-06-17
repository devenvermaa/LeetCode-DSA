/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode sufficientSubset(TreeNode root, int limit) {
        // Base case: if the node is null, just return null
        if (root == null) {
            return null;
        }
        
        // If we reached a leaf node, check if its value meets the remaining limit
        if (root.left == null && root.right == null) {
            return root.val < limit ? null : root;
        }
        
        // Recursively update the left and right subtrees 
        // by subtracting the current node's value from the limit
        root.left = sufficientSubset(root.left, limit - root.val);
        root.right = sufficientSubset(root.right, limit - root.val);
        
        // If both children are null, it means no path passing through 
        // this node is sufficient. So, we delete this node by returning null.
        if (root.left == null && root.right == null) {
            return null;
        }
        
        // Otherwise, at least one path is sufficient, keep the node
        return root;
    }
}
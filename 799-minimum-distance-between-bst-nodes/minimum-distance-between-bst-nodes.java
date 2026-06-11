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
    // Tracks the minimum difference found so far
    private int minDiff = Integer.MAX_VALUE;
    // Tracks the value of the previously visited node
    private Integer prev = null;

    public int minDiffInBST(TreeNode root) {
        inorder(root);
        return minDiff;
    }

    private void inorder(TreeNode node) {
        if (node == null) {
            return;
        }

        // 1. Traverse the left subtree
        inorder(node.left);

        // 2. Process the current node
        if (prev != null) {
            minDiff = Math.min(minDiff, node.val - prev);
        }
        prev = node.val; // Update prev to the current node's value

        // 3. Traverse the right subtree
        inorder(node.right);
    }
}
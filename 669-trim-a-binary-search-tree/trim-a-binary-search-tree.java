class Solution {
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) return null;
        
        // If root value is less than low, the valid subtree lies to the right
        if (root.val < low) {
            return trimBST(root.right, low, high);
        }
        // If root value is greater than high, the valid subtree lies to the left
        if (root.val > high) {
            return trimBST(root.left, low, high);
        }
        
        // Root is within bounds, process its children
        root.left = trimBST(root.left, low, high);
        root.right = trimBST(root.right, low, high);
        
        return root;
    }
}
class Solution {
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        return findMin(root, root.val);
    }
    
    private int findMin(TreeNode node, int minVal) {
        if (node == null) return -1;
        if (node.val > minVal) return node.val;
        
        int left = findMin(node.left, minVal);
        int right = findMin(node.right, minVal);
        
        if (left != -1 && right != -1) return Math.min(left, right);
        return left != -1 ? left : right;
    }
}
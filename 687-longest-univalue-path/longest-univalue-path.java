class Solution {
    int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxPath;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;

        int left = dfs(node.left);
        int right = dfs(node.right);

        int arrowLeft = 0, arrowRight = 0;

        if (node.left != null && node.left.val == node.val) {
            arrowLeft = left + 1;
        }
        if (node.right != null && node.right.val == node.val) {
            arrowRight = right + 1;
        }

        maxPath = Math.max(maxPath, arrowLeft + arrowRight);
        return Math.max(arrowLeft, arrowRight);
    }
}
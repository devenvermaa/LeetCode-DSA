class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1;
        int[] sums = new int[m];
        
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum += nums[i];
            if (i >= k) currentSum -= nums[i - k];
            if (i >= k - 1) sums[i - k + 1] = currentSum;
        }

        int[] left = new int[m];
        int bestLeftIdx = 0;
        for (int i = 0; i < m; i++) {
            if (sums[i] > sums[bestLeftIdx]) bestLeftIdx = i;
            left[i] = bestLeftIdx;
        }

        int[] right = new int[m];
        int bestRightIdx = m - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (sums[i] >= sums[bestRightIdx]) bestRightIdx = i;
            right[i] = bestRightIdx;
        }

        int[] result = new int[]{-1, -1, -1};
        int maxTotalSum = 0;

        for (int j = k; j < m - k; j++) {
            int l = left[j - k];
            int r = right[j + k];
            int total = sums[l] + sums[j] + sums[r];
            if (total > maxTotalSum) {
                maxTotalSum = total;
                result[0] = l;
                result[1] = j;
                result[2] = r;
            }
        }
        return result;
    }
}
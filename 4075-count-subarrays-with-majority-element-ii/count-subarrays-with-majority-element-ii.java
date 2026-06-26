import java.util.*;

public class Solution {
    // 1. Changed method name from countSubarrays to countMajoritySubarrays
    public long countMajoritySubarrays(int[] nums, int target) {
        int n = nums.length;
        
        // Step 1: Compute prefix sums where target is +1 and others are -1
        // 2. Fixed sizing bug: allocated n + 1 elements
        int[] pref = new int[n + 1];
        pref[0] = 0;
        for (int i = 0; i < n; i++) {
            int val = (nums[i] == target) ? 1 : -1;
            pref[i + 1] = pref[i] + val;
        }
        
        // Step 2: Coordinate compression since pref values can be negative
        int[] sortedUnique = pref.clone();
        Arrays.sort(sortedUnique);
        
        // Remove duplicates to find the rank bounds
        int uniqueCount = 0;
        for (int i = 0; i < sortedUnique.length; i++) {
            if (i == 0 || sortedUnique[i] != sortedUnique[i - 1]) {
                sortedUnique[uniqueCount++] = sortedUnique[i];
            }
        }
        
        // Step 3: Use Fenwick Tree to count pairs where pref[j] > pref[i]
        FenwickTree bit = new FenwickTree(uniqueCount);
        long totalSubarrays = 0;
        
        for (int p : pref) {
            int rank = binarySearch(sortedUnique, uniqueCount, p) + 1;
            totalSubarrays += bit.query(rank - 1);
            bit.update(rank, 1);
        }
        
        return totalSubarrays;
    }
    
    private int binarySearch(int[] arr, int size, int target) {
        int left = 0, right = size - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) return mid;
            else if (arr[mid] < target) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }
    
    class FenwickTree {
        private int[] tree;
        private int size;
        
        public FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 1];
        }
        
        public void update(int index, int delta) {
            while (index <= size) {
                tree[index] += delta;
                index += index & (-index);
            }
        }
        
        public int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= index & (-index);
            }
            return sum;
        }
    }
}
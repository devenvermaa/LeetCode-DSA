import java.util.PriorityQueue;

class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        
        // Min-heap storing array of indices: {numerator_idx, denominator_idx}
        // Sorted by the floating-point value of arr[numerator_idx] / arr[denominator_idx]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> 
            Double.compare((double) arr[a[0]] / arr[a[1]], (double) arr[b[0]] / arr[b[1]])
        );
        
        // Initialize heap with the smallest fraction for each possible denominator
        for (int j = 1; j < n; j++) {
            pq.offer(new int[]{0, j});
        }
        
        // Pop the smallest element k - 1 times
        for (int count = 0; count < k - 1; count++) {
            int[] curr = pq.poll();
            int i = curr[0];
            int j = curr[1];
            
            // If there's a next larger numerator for this denominator, push it to the heap
            if (i + 1 < j) {
                pq.offer(new int[]{i + 1, j});
            }
        }
        
        // The top of the heap is now our k-th smallest fraction
        int[] resultIndices = pq.peek();
        return new int[]{arr[resultIndices[0]], arr[resultIndices[1]]};
    }
}
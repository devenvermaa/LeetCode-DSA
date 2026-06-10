import java.util.Random;

class Solution {
    private double radius;
    private double x_center;
    private double y_center;
    private Random random;

    public Solution(double radius, double x_center, double y_center) {
        this.radius = radius;
        this.x_center = x_center;
        this.y_center = y_center;
        this.random = new Random();
    }
    
    public double[] randPoint() {
        while (true) {
            // Generate coordinates in a square bounding box of [-radius, radius]
            // random.nextDouble() returns a value between 0.0 and 1.0
            double x = (random.nextDouble() * 2 * radius) - radius;
            double y = (random.nextDouble() * 2 * radius) - radius;
            
            // Check if the point resides inside the unit circle boundary
            if (x * x + y * y <= radius * radius) {
                // Shift the point relative to the actual center
                return new double[]{x_center + x, y_center + y};
            }
        }
    }
}
public class Solution {
    public boolean validUtf8(int[] data) {
        // Tracks how many continuation bytes (starting with '10') we are expecting
        int numberOfBytesToProcess = 0;

        // Masks to check the most significant bits of a byte
        int mask1 = 1 << 7; // 10000000
        int mask2 = 1 << 6; // 01000000

        for (int num : data) {
            // If we are not expecting continuation bytes, this is the start of a new UTF-8 character
            if (numberOfBytesToProcess == 0) {
                int mask = 1 << 7;
                
                // Count how many leading 1s are there in the current byte
                while ((num & mask) != 0) {
                    numberOfBytesToProcess++;
                    mask >>= 1;
                }

                // 1-byte character (starts with 0xxxxxxx)
                if (numberOfBytesToProcess == 0) {
                    continue;
                }

                // UTF-8 characters can only be 2, 3, or 4 bytes long.
                // A single leading '1' (numberOfBytesToProcess == 1) or more than 4 is invalid.
                if (numberOfBytesToProcess > 4 || numberOfBytesToProcess == 1) {
                    return false;
                }
                
            } else {
                // If we are expecting a continuation byte, it must start with '10'
                // This means the 7th bit must be 1, and the 6th bit must be 0.
                if (!((num & mask1) != 0 && (num & mask2) == 0)) {
                    return false;
                }
            }
            
            // We successfully processed one byte of the current UTF-8 character sequence
            numberOfBytesToProcess--;
        }

        // If we processed everything correctly, we shouldn't be waiting for any more continuation bytes
        return numberOfBytesToProcess == 0;
    }
}
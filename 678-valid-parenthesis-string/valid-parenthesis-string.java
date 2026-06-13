class Solution {
    public boolean checkValidString(String s) {
        int low = 0, high = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                low++;
                high++;
            } else if (c == ')') {
                low--;
                high--;
            } else { // '*' case
                low--;
                high++;
            }
            if (high < 0) return false; // More closing brackets than available open and stars
            if (low < 0) low = 0; // low cannot fall below 0 since we can treat * as empty
        }
        return low == 0;
    }
}
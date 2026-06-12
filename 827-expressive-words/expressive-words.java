import java.util.ArrayList;
import java.util.List;

class Solution {
    public int expressiveWords(String s, String[] words) {
        List<Character> sChars = new ArrayList<>();
        List<Integer> sCounts = new ArrayList<>();
        getRLE(s, sChars, sCounts);
        
        int ans = 0;
        for (String word : words) {
            List<Character> wChars = new ArrayList<>();
            List<Integer> wCounts = new ArrayList<>();
            getRLE(word, wChars, wCounts);
            
            if (!wChars.equals(sChars)) continue;
            
            boolean isValid = true;
            for (int i = 0; i < sCounts.size(); i++) {
                int sc = sCounts.get(i);
                int wc = wCounts.get(i);
                if (wc > sc || (wc < sc && sc < 3)) {
                    isValid = false;
                    break;
                }
            }
            if (isValid) ans++;
        }
        return ans;
    }
    
    private void getRLE(String str, List<Character> chars, List<Integer> counts) {
        for (char c : str.toCharArray()) {
            if (chars.isEmpty() || chars.get(chars.size() - 1) != c) {
                chars.add(c);
                counts.add(1);
            } else {
                counts.set(counts.size() - 1, counts.get(counts.size() - 1) + 1);
            }
        }
    }
}
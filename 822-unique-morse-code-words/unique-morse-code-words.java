import java.util.HashSet;

class Solution {
    public int uniqueMorseRepresentations(String[] words) {
        String[] MORSE = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        HashSet<String> seen = new HashSet<>();
        
        for (String word : words) {
            StringBuilder sb = new StringBuilder();
            for (char c : word.toCharArray()) {
                sb.append(MORSE[c - 'a']);
            }
            seen.add(sb.toString());
        }
        
        return seen.size();
    }
}
class MagicDictionary {
    private Set<String> dict;

    public MagicDictionary() {
        dict = new HashSet<>();
    }
    
    public void buildDict(String[] dictionary) {
        for (String word : dictionary) {
            dict.add(word);
        }
    }
    
    public boolean search(String searchWord) {
        char[] chars = searchWord.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char original = chars[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == original) continue;
                chars[i] = c;
                if (dict.contains(new String(chars))) {
                    return true;
                }
            }
            chars[i] = original; // backtracking step
        }
        return false;
    }
}
import java.util.*;

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> counts = new HashMap<>();
        
        for (String cd : cpdomains) {
            int spaceIdx = cd.indexOf(' ');
            int count = Integer.parseInt(cd.substring(0, spaceIdx));
            String domain = cd.substring(spaceIdx + 1);
            
            // Loop through the domain to find all subdomains
            int i = 0;
            while (i >= 0) {
                String subdomain = domain.substring(i);
                counts.put(subdomain, counts.getOrDefault(subdomain, 0) + count);
                // Move to the next component after the dot
                i = domain.indexOf('.', i) + 1;
                // If there are no more dots, domain.indexOf returns -1, making i = 0, which breaks the loop
                if (i == 0) break;
            }
        }
        
        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            result.add(entry.getValue() + " " + entry.getKey());
        }
        return result;
    }
}
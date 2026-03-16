import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DNA_Sequences {
    public static boolean[] getSequence(String[][] dna) {
        int n = dna.length;
        boolean[] res = new boolean[n];

        
        for (int i = 0; i < n; i++) {
            String s1 = dna[i][0];
            String s2 = dna[i][1];

            Map<Character, Integer> map1 = new HashMap<>();
            for (Character b : s1.toCharArray()) {
                map1.put(b, map1.getOrDefault(b, 0) + 1);
            }
            Map<Character, Integer> map2 = new HashMap<>();
            for (Character b : s2.toCharArray()) {
                map2.put(b, map2.getOrDefault(b, 0) + 1);
            }

            Set<Character> keys1 = map1.keySet();
            Set<Character> keys2 = map2.keySet();

            if (keys1.size() - keys2.size() > 1) {
                res[i] = false;
            } else {
                int allowed = 0;
                for (Character character : keys1) {
                    if (keys2.contains(character) && (map1.get(character) == map2.get(character)))
                        continue;
                    else {
                        allowed++;
                    }
                }
                if (allowed > 1)
                    res[i] = false;
                else
                    res[i] = true;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        String[][] dna = { { "abcee", "acdeedb" } };
        boolean[] res = getSequence(dna);
        System.out.println(res[0]);
    }
}

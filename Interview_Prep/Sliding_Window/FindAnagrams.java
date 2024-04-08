package Sliding_Window;

import java.util.*;

public class FindAnagrams {
    public List<Integer> findAngrams(String s, String p) {
        int[] scount = new int[26];
        int[] pcount = new int[26];
        List<Integer> result = new ArrayList<>();
        int start = 0, end = p.length();
        if (s.length() < p.length())
            return result;
        for (int i = 0; i < p.length(); i++) {
            scount[s.charAt(i) - 'a']++;
            pcount[p.charAt(i) - 'a']++;
        }
        if (Arrays.equals(scount, pcount))
            result.add(start);

        while (end < s.length()) {
            scount[s.charAt(start) - 'a']--;
            scount[s.charAt(end) - 'a']++;

            if (Arrays.equals(scount, pcount))
                result.add(start + 1);
            start++;
            end++;
        }
        return result;
    }

    public static void main(String[] args) {
        // int no_of_tests;
        Scanner sc = new Scanner(System.in);
        // no_of_tests = sc.nextInt();
        FindAnagrams obj = new FindAnagrams();
        // while (no_of_tests != 0) {
            String s = sc.nextLine();
            String p = sc.nextLine();
            List<Integer> res = obj.findAngrams(s, p);
            for (Integer integer : res) {
                System.out.print(integer);
            }
        //     no_of_tests--;
        // }
    }
}

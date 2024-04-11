package Sliding_Window;

import java.util.*;

public class Longest_repeating_character_replacement {
    public static int characterReplacement(String s, int k) {
        int max_count=Integer.MIN_VALUE, size=Integer.MIN_VALUE,start=0,end=0;
        int[] count = new int[26];
        while(end < s.length()) {
            max_count = Integer.max(max_count,++count[s.charAt(end)-'A']);
            if(end-start+1-max_count > k) {
                count[s.charAt(start)-'A']--;
                start++;
            }
            size = Integer.max(end-start+1, size);
            end++;
        }
        return size;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        while (tests-- != 0) {
            String s = sc.next();
            int k = sc.nextInt();
            System.out.println(characterReplacement(s, k));
        }
    }
}

package String_Processing;

import java.util.*;

public class KMP {

    private void constructLPS(String pattern, int[] lps) {
        int j = 0;
        int i = 1;
        lps[0] = 0;
        while (i < pattern.length()) {
            if (pattern.charAt(j) == pattern.charAt(i)) {
                j++;
                lps[i] = j;
                i++;
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                    lps[i] = 0;
                }
            }
        }
    }

    private void KMPSearch(String pattern, String text) {
        int m = pattern.length();
        int[] lps = new int[m];
        constructLPS(pattern, lps);

        int i = 0, j = 0;
        while (i < text.length()) {
            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;

                if (j == m) {
                    System.out.println("Pattern found at index " + (i - j));
                    j = lps[j - 1];
                }
            } else {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        int tests = sc.nextInt();
        KMP k = new KMP();
        while (tests-- != 0) {
            System.out.println("Enter Pattern and text");
            String pattern = sc.next();
            String text = sc.next();
            k.KMPSearch(pattern, text);
            System.out.println();
        }
    }

}

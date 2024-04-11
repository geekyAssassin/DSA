package Sliding_Window;

import java.util.*;

public class No_Of_Substrings_With_all_Characters {
    public static int numberOfSubstrings(String s) {
        int string_count = 0, start = 0, end = 0, n = s.length();
        int[] count = new int[3];
        while (end < n) {
            count[s.charAt(end) - 'a']++;
            while (count[0] > 0 && count[1] > 0 && count[2] > 0) {
                string_count += n - end;
                count[s.charAt(start++) - 'a']--;
            }
            end++;
        }
        return string_count;

    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();

            System.out.println(numberOfSubstrings(input));
            no_of_tests--;
        }
    }
}

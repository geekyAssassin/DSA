package Sliding_Window;

import java.util.*;

public class Equal_Substrin_within_Budget {
    public static int equalSubstring(String s, String t, int maxCost) {
        int curr_cost = 0, start = 0, end = 0;
        while (end < s.length()) {
            curr_cost += Math.abs(s.charAt(end) - t.charAt(end));
            if (curr_cost > maxCost) {
                curr_cost -= Math.abs(s.charAt(start) - t.charAt(start));
                start++;
            }
            end++;
        }
        return end - start;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // int tests = sc.nextInt();
        // while (tests-- != 0) {
            String s = sc.nextLine();
            String t = sc.nextLine();
            int maxCost = sc.nextInt();
            System.out.println(equalSubstring(s, t, maxCost));
        // }
    }
}

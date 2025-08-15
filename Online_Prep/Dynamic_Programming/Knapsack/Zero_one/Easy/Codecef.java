package Dynamic_Programming.Knapsack.Zero_one.Easy;

import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef {
    public static void main(String[] args) throws java.lang.Exception {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int amount = sc.nextInt();
            int[] notes = new int[n];
            for (int i = 0; i < n; i++) {
                notes[i] = sc.nextInt();
            }

            int[][] dp = new int[n + 1][amount + 1];
            for (int i = 0; i <= n; i++) {
                Arrays.fill(dp[i], -1);
            }
            String res = isSubset(n, amount, notes, dp) == true ? "Yes" : "No";

            System.out.println(res);
        }
        sc.close();

    }

    private static boolean isSubset(int n, int amount, int[] notes, int[][] dp) {
        if (n < 0 || amount < 0)
            return false;

        if (n == 0) {
            if (amount == 0) {
                dp[n][amount] = 1;
                return true;
            } else {
                dp[n][amount] = 0;
                return false;
            }
        }

        if (dp[n][amount] != -1)
            return dp[n][amount] == 1 ? true : false;

        boolean include = isSubset(n - 1, amount - notes[n - 1], notes, dp);
        boolean exclude = isSubset(n - 1, amount, notes, dp);

        dp[n][amount] = include || exclude ? 1 : 0;
        return include || exclude;
    }
}

public class Travelling_Salesman_Problem {
    public static int getTotalCost(int[][] cost, int index, int mask, int n, int[][] dp) {
        if (mask == (1 << n))
            return cost[index][0];
        if (dp[index][mask] != -1)
            return dp[index][mask];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                ans = Math.min(ans, cost[index][i] + getTotalCost(cost, i, (mask | (1 << i)), ans, dp));
            }
        }
        return dp[index][mask] = ans;
    }

    public static void main(String[] args) {

    }
}

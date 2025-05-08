class Solution {
    public int solution(int n, int[] tops) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = tops[0] == 0 ? 3 : 4;

        for (int i = 2; i <= n; i++) {
            int mulValue = tops[i - 1] == 0 ? 3 : 4;

            dp[i] = (dp[i - 1] * mulValue - dp[i - 2]) % 10007;
            dp[i] = dp[i] + (dp[i] < 0 ? 10007 : 0);
        }

        return dp[n];
    }
}
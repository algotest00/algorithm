class Solution {
    public int solution(int[][] info, int n, int m) {
        Integer[][] dp = new Integer[info.length + 1][m];
        for (int j = 0; j < m; j++) {
            dp[0][j] = 0;
        }

        for (int i = 1; i <= info.length; i++) {
            int a = info[i - 1][0];
            int b = info[i - 1][1];
            for (int j = 0; j < m; j++) {
                // A가 훔친 경우로 초기화
                if (dp[i - 1][j] == null) dp[i][j] = a;
                else dp[i][j] = dp[i - 1][j] + a;
            }

            for (int j = 0; j < m; j++) {
                // A가 훔친 경우보다 B가 훔친 경우가 더 적으면 수정
                if (j + b < m) {
                    dp[i][j + b] = Math.min(dp[i - 1][j], dp[i][j + b]);
                }
            }
        }

        if (dp[info.length][m - 1] == null || dp[info.length][m - 1] >= n) return -1;
        return dp[info.length][m - 1];
    }
}
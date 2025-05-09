class Solution {
    public int solution(int n, int[] tops) {
        int answer = 0;
        // dp[i][0] = 우하단 세모가 유지되지 않은 경우(마름모에 포함됨)
        // dp[i][1] = 우하단 세모가 유지되어 이후 사용 가능한 경우
        int[][] dp = new int[n+1][2];
        
        dp[0][0] = 0;
        dp[0][1] = 1;
        
        for(int i=1;i<=n;i++){
            
            dp[i][0]= (dp[i-1][0] + dp[i-1][1]) %10007;
            
            dp[i][1]= (tops[i-1]==1 ? 2*dp[i-1][0] + 3 * dp[i-1][1] : dp[i-1][0] + 2 * dp[i-1][1])%10007 ;
        }
        answer = (dp[n][0] + dp[n][1])%10007;
        return answer;
    }
}
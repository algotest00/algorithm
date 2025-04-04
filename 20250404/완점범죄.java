import java.util.*;

/*
 * A의 흔적은 테이블의 value로, B의 흔적은 index로 남긴다. => i는 항상 다음단계로 진행을 의미, j는 B흔적의 총합합
 * A도둑이 훔칠 때, B도둑이 훔칠 때의 과정을 점화식으로 나타내야함.
 * A도둑이 누적흔적(dp[i][j]) -> dp[i][j] = min(dp[i][j], dp[i-1][j]+A의흔적)
 * ==> dp[i-1][j]+A의흔적 = A가 흔적을 남겼으므로 흔적을 새로 갱신한다.
 * B도둑이 누적흔적(dp[i][j+B의흔적)]) -> dp[i][j+B의흔적] = min(dp[i][j+b의 흔적], dp[i-1][j]) //단 j + B의 흔적은 m보다 작아야한다.
 * ==> dp[i-1][j] = A는 흔적을 남기지 않았으므로 이전단계의 A의 누적 흔적을 가져온다.
 */
class Solution {
    static final int INF = 120;

    public int solution(int[][] info, int n, int m) {
        int itemCount = info.length;
        int[][] dp = new int[itemCount + 1][m]; 

        for (int i = 0; i <= itemCount; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][0] = 0;

        for (int i = 1; i <= itemCount; i++) {
            int aTrace = info[i - 1][0]; // A가 훔칠 때 남기는 흔적
            int bTrace = info[i - 1][1]; // B가 훔칠 때 남기는 흔적
            for (int j = 0; j < m; j++) {
                if (dp[i - 1][j] == INF) continue;
                int newAtrace = dp[i-1][j] + aTrace;
                dp[i][j] = Math.min(dp[i][j], newAtrace);

                //B를선택했을때
                int newB = j + bTrace;
                if(newB < m) {
                    dp[i][newB] = Math.min(dp[i][newB], dp[i-1][j]); //이전단게의 A만 가져오고 idx는 B만 증가하고.
                }
            }
        }

        int minATrace = INF;
        for (int b = 0; b < m; b++) {
            minATrace = Math.min(minATrace, dp[itemCount][b]);
        }

        return (minATrace >= n) ? -1 : minATrace;
    }
}

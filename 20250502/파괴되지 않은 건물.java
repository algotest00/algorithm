import java.util.*;

class Solution {
    
    private static int N, M;
    private static int[][] Imos;
    private static int[][] Sums;
    private static int Answer;
    
    public int solution(int[][] board, int[][] skill) {
        N = board.length;
        M = board[0].length;
        Imos = new int[N + 1][M + 1];
        Sums = new int[N + 1][M + 1];
        
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            int r1 = skill[i][1];
            int c1 = skill[i][2];
            int r2 = skill[i][3] + 1;
            int c2 = skill[i][4] + 1;
            int degree = skill[i][5];
            
            if (type == 1) {
                degree = -degree;
            }
            
            Imos[r1][c1] += degree;
            Imos[r1][c2] -= degree;
            Imos[r2][c1] -= degree;
            Imos[r2][c2] += degree;
        }
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                Sums[i][j] = Sums[i - 1][j] + Sums[i][j - 1] - Sums[i - 1][j - 1] + Imos[i - 1][j - 1];
                if (board[i - 1][j - 1] + Sums[i][j] >= 1) {
                    Answer++;
                }
            }
        }
        
        return Answer;
    }
}
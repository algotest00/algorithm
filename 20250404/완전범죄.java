import java.util.*;

class Solution {
    
    int[][] infos;
    int N, M, len;
    boolean[][][] vis;
    int result = Integer.MAX_VALUE;

    public int solution(int[][] info, int n, int m) {
        infos = info;
        N = n;
        M = m;
        len = info.length;
        
        vis = new boolean[len][N + 1][M + 1];

        dfs(0, 0, 0);
        if (result == Integer.MAX_VALUE) {
            return -1;
        }
        else return result;
    }

    void dfs(int cur, int a, int b) {
        if (a >= N || b >= M) return;
        if (cur == len) {
            result = Math.min(a,result);
            return;
        }
        if (vis[cur][a][b]) return;
        vis[cur][a][b] = true;

        dfs(cur + 1, a + infos[cur][0], b);
        dfs(cur + 1, a, b + infos[cur][1]);

    }
}
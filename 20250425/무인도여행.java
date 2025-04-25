import java.util.*;
/**
 * DFS와 방향 배열(dir)을 이용해 무인도별 식량 총합을 구하는 문제.
 * 상하좌우로 연결된 숫자들을 하나의 섬으로 보고 DFS로 탐색하며 합산.
 * 모든 섬의 총합을 오름차순 정렬하여 반환한다.
 */
class Solution {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static boolean[][] isVisited;
    
    public int[] solution(String[] maps) {
        ArrayList<Integer> answer = new ArrayList<>();
        int n = maps.length;
        int m = maps[0].length();
        isVisited = new boolean[n][m];
        
        for (int i = 0 ; i < n; i++) {
            for (int j = 0; j < m; j++) {
            if (maps[i].charAt(j) == 'X' || isVisited[i][j]) {
                continue;
            }
                answer.add(moveIsland(i, j, n, m, maps));
            }
        }
        return answer.isEmpty() ? new int[]{-1} 
                        : answer.stream().mapToInt(i -> i).sorted().toArray();
    }
    
    private int moveIsland(int x, int y, int n, int m, String[] maps) {
        isVisited[x][y] = true;
        int sum = maps[x].charAt(y) - '0';
        for (int k = 0; k < 4; k++) {
            int dx = x + dir[k][0];
            int dy = y + dir[k][1];
            if(dx < 0 || dy < 0 || dx >= n || dy >= m) continue;
            if(isVisited[dx][dy]) continue;
            if (maps[dx].charAt(dy) == 'X') continue;
            sum += moveIsland(dx,dy,n,m,maps);
        }
        
        return sum;
    }
}
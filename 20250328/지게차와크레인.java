/* 시뮬레이션 + DFS(깊이 우선 탐색)
 * 풀이시간 2시간
 * 지울 수 있는가에 대한 로직을 잘 못 이해했음=> 문제 조건을 더 정확히 파악하고 시작할 것!
 * - 요청 단어 길이 == 1 → DFS 탐색을 통해 외부와 연결되었는지 확인 후 제거
 * - 요청 단어 길이 >= 2 → 조건 없이 해당 문자를 모두 제거
 */
import java.util.*;
class Solution {
    static int[][] dir = {{1,0},{0,1},{-1,0},{0,-1}};
    static char[][] map;
    static boolean[][] visited;
    static int n,m;
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        n = storage.length;
        m = storage[0].length();
        map = new char[n][m];
        visited = new boolean[n][m];
        
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++) {
                map[i][j] = storage[i].charAt(j);
            }
        }

        for(int i = 0; i < requests.length; i++) {
            char word = requests[i].charAt(0);
            int wordLength = requests[i].length();
            
            char[][] tempMap = new char[n][m];
            for (int x = 0; x < n; x++) {
                tempMap[x] = map[x].clone();
            }
            
            if (wordLength == 1) {
               
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < m; y++) {
                         visited = new boolean[n][m]; 
                        if (checkRemove(x, y) && map[x][y] == word){
                            tempMap[x][y] = '.';
                        }
                        
                    }
                }
            }else {
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < m; y++) {
                        if (map[x][y] == word){
                            tempMap[x][y] = '.';
                        }
                    }
                }
            }
            
            for (int x = 0; x < n; x++) {
                for (int y = 0; y < m; y++) {
                    map[x][y] = tempMap[x][y];
                }
            }
            
        }
        
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                if(map[x][y] != '.'){
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    static boolean checkRemove(int x, int y){
        if (visited[x][y]) return false;
        visited[x][y] = true;
        for (int k = 0; k < 4; k++){
            int dx = x + dir[k][0];
            int dy = y + dir[k][1];
            if (dx < 0 || dy < 0 || dx >= n || dy >= m) return true;
            if (map[dx][dy] != '.') continue;
            if (checkRemove(dx, dy)) return true;
        }
        return false;
    }
 
}
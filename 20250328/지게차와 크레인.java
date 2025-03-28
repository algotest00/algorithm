// 크레인으로 꺼낼때 => 요청이 BB 이런식으로 알파벳 두개로 붙어서 나옴
// 지게차로 꺼낼때 => 요청 그냥 하나
//결과 : 남은 갯수
// storage : 1차원 문자배열(들어있는거) -> 2차원 배열로 만들어서 처리하기
//  request : 1차원 문자배열 (요청들어온것)



import java.util.*;
class Solution {
    public int solution(String[] storage, String[] requests) {
        int answer = 0;
        int n = storage.length;
        int m =0;
        String [][] twoStorage =new String[storage.length][];
        for(int i =0; i<storage.length;i++){
             twoStorage[i] = storage[i].split("");
            if(i == 0){
                m= storage[i].length();
            }
        }
        
             // 이동 방향 (상, 하, 좌, 우)
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
 
        for(int i =0; i<requests.length;i++){
            char target = requests[i].charAt(0);
            
            if(requests[i].length() == 1){
                //지게차
                 boolean[][] visited = new boolean[n][m];

                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m; k++) {
                        if (!twoStorage[j][k].equals("0") && twoStorage[j][k].charAt(0) == target) {
                            // 4방향 중 하나라도 외부와 연결되면 제거 가능
                            if (isAccessible(twoStorage, j, k, n, m, dx, dy,visited)) {
                                System.out.println("확인"+j+k+visited[j][k]);
                                visited[j][k] = true;
                            }
                        }
                    }
                }
                
                
                // 반영된거 한번에 제거
                   for (int s = 0; s < n; s++) {
                    for (int l = 0; l < m; l++) {
                        if (visited[s][l]) {
                            twoStorage[s][l] = "0";
                        }
                    }
                }
                
                
                
            }else{
                //크레인
                // 뺀거 0으로 치환
                for(int j =0; j<n;j++){
                    for(int k =0;  k<m;k++ ){
                        if(twoStorage[j][k].equals(String.valueOf(target))){
                            twoStorage[j][k] = "0";
                        }
                    }
                }
                
            }
            
        }
        
        // answer = n*m -0의 갯수
         for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!twoStorage[i][j].equals("0")) {
                    answer++;
                }
            }
        }

        
        return answer;
    }
    
 private boolean isAccessible(String[][] storage, int x, int y, int n, int m, int[] dx, int[] dy, boolean[][] visited) {
    if (x == 0 || x == n - 1 || y == 0 || y == m - 1) {
        return true; // 바깥쪽이면 무조건 접근 가능
    }

    // 4방향 체크
    for (int i = 0; i < 4; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        // 경계 체크 및 방문하지 않은 빈 공간일 때
        if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny]) {
            if (storage[nx][ny].equals("0")) {
                // 주변이 비어있고, 이 공간이 외부와 연결되었는지 확인
                visited[nx][ny] = true;
                if (isAccessible(storage, nx, ny, n, m, dx, dy, visited)) {
                    return true; // 재귀적으로 연결된 공간을 찾으면 접근 가능
                }
            }
        }
    }
    return false;
}
}

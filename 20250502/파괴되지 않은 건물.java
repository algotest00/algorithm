// [2차원배열에서의 누적합이용]
// 네 꼭짓점에서 변화량을 조작하여 사각형 영역 전체에 degree를 반영:
// - (r1, c1): 변화 시작점 → +degree
// - (r1, c2+1): 열 경계 종료 → -degree
// - (r2+1, c1): 행 경계 종료 → -degree
// - (r2+1, c2+1): 중복으로 빠진 코너 → +degree 복구
class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;

        // 누적합 처리를 위한 map 배열 (board 크기 +1 이상 여유 공간 필요)
        int[][] map = new int[board.length + 2][board[0].length + 2];

        // 1. skill 적용
        for (int[] s : skill) {
            int type = s[0] == 1 ? -1 : 1; // 1: 공격(-), 2: 회복(+)
            int r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4];
            int degree = type * s[5];
            map[r1][c1] += degree;
            map[r1][c2 + 1] -= degree;
            map[r2 + 1][c1] -= degree;
            map[r2 + 1][c2 + 1] += degree;
        }

        // 2. 세로 방향 누적합
        for (int i = 1; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                map[i][j] += map[i - 1][j];
            }
        }

        // 3. 가로 방향 누적합
        for (int i = 0; i < map.length; i++) {
            for (int j = 1; j < map[0].length; j++) {
                map[i][j] += map[i][j - 1];
            }
        }

        // 4. board에 누적합을 적용하고 파괴되지 않은 건물 수 세기
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] + map[i][j] >= 1) {
                    answer++;
                }
            }
        }

        return answer;
    }
}

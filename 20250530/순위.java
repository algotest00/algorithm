class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n + 1][n + 1];
        boolean[][] lose = new boolean[n + 1][n + 1];

        // 경기 결과 기록
        for (int[] result : results) {
            win[result[0]][result[1]] = true;
            lose[result[1]][result[0]] = true;
        }

        int answer = 0;
        // 선수별 승패 체크
        for (int i = 1; i <= n; i++) {
            int winCount = dfs(i, win, new boolean[n + 1]);
            int loseCount = dfs(i, lose, new boolean[n + 1]);

            if (winCount + loseCount == n - 1) answer++;
        }

        return answer;
    }

    // player 선수가 이긴 상대 수를 체크
    private int dfs(int player, boolean[][] maps, boolean[] visited) {
        // 방문 처리
        visited[player] = true;

        int count = 0;
        for (int i = 1; i < maps.length; i++) {
            if (maps[player][i] && !visited[i]) {
                count += (dfs(i, maps, visited) + 1);
            }
        }

        return count;
    }
}

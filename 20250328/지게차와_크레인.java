import java.util.*;

class Solution {
    int[][] d = {{0, -1}, {-1, 0}, {1, 0}, {0, 1}};

    public int solution(String[] storage, String[] requests) {
        int rowCount = storage.length;
        int colCount = storage[0].length();

        char[][] map = Arrays.stream(storage).map(String::toCharArray).toArray(char[][]::new);

        for (String request : requests) {
            char[] cArr = request.toCharArray();
            // 크레인 : 전체 변경
            if (cArr.length == 2) {
                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        if (map[row][col] == cArr[0]) map[row][col] = '\0';
                    }
                }
            }

            // 지게차 : 외부에서 접근 가능한 것만 변경
            if (cArr.length == 1) {
                List<int[]> takeOutBuffer = new ArrayList<>();

                for (int row = 0; row < rowCount; row++) {
                    for (int col = 0; col < colCount; col++) {
                        if (map[row][col] == cArr[0]) {
                            // BFS
                            Queue<int[]> queue = new LinkedList<>();
                            queue.add(new int[]{row, col});
                            // 방문 처리
                            boolean[][] visited = new boolean[rowCount][colCount];
                            visited[row][col] = true;

                            // 꺼낼 수 있는지 플래그
                            boolean canTakeOut = false;
                            while (!queue.isEmpty()) {
                                int[] pos = queue.poll();
                                // 꺼낼 수 있으면 플래그 변경 및 탐색 종료
                                if (pos[0] == 0 || pos[1] == 0 || pos[0] == rowCount - 1 || pos[1] == colCount - 1) {
                                    canTakeOut = true;
                                    break;
                                }

                                for (int i = 0; i < 4; i++) {
                                    // 다음 방향 인덱스 계산
                                    int dr = pos[0] + d[i][0];
                                    int dc = pos[1] + d[i][1];
                                    if (dr >= 0 && dr < rowCount && dc >= 0 && dc < colCount
                                            // 방문 안했고 길 열려있으면
                                            && !visited[dr][dc] && map[dr][dc] == '\0'
                                    ) {
                                        // 다음 탐색지 등록 및 방문처리
                                        queue.add(new int[]{dr, dc});
                                        visited[dr][dc] = true;
                                    }
                                }
                            }

                            // 꺼낼 수 있으면 버퍼에 저장
                            if (canTakeOut) takeOutBuffer.add(new int[]{row, col});
                        }
                    }
                }

                // 버퍼에 있는 컨테이너들 꺼내기 처리
                takeOutBuffer.forEach(buff -> map[buff[0]][buff[1]] = '\0');
            }
        }

        int answer = 0;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < colCount; col++) {
                if (map[row][col] != '\0') answer++;
            }
        }

        return answer;
    }
}

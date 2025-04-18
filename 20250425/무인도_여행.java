import java.util.*;

class Solution {
    int[][]     d = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    char[][]    m;
    boolean[][] visited;

    public int[] solution(String[] maps) {
        m = Arrays.stream(maps).map(String::toCharArray).toArray(char[][]::new);
        visited = new boolean[m.length][m[0].length];

        List<Integer> answer = new ArrayList<>();
        for (int y = 0; y < m.length; y++) {
            for (int x = 0; x < m[0].length; x++) {
                int sum = visit(x, y);
                if (sum > 0) answer.add(sum);
            }
        }

        return answer.size() <= 0
                ? new int[]{-1}
                : answer.stream().sorted().mapToInt(i -> i).toArray();
    }

    // 방문처리 및 방문한 땅의 식량 합산 반환
    int visit(int x, int y) {
        boolean isOut = !(y >= 0 && y < m.length && x >= 0 && x < m[0].length);
        if (isOut || 'X' == m[y][x] || visited[y][x]) return 0;

        visited[y][x] = true;
        int sum = m[y][x] - '1' + 1;
        for (int i = 0; i < 4; i++) sum += visit(x + d[i][0], y + d[i][1]);

        return sum;
    }
}
import java.util.*;

class Solution {
    List<int[]> list = new ArrayList<>();
    public int[][] solution(int n) {
        // n개의 원판을 1번 기둥에서 3번 기둥으로 옮겨라. 남은 기둥은 2
        action(n, 1, 3, 2);
        int[][] answer = list.stream().toArray(int[][]::new);
        return answer;
    }

    /**
     * @param n     총 옮길 원판의 수
     * @param from  출발 기둥
     * @param to    목표 기둥
     * @param tmp   출발,목표 기둥 제외하고 남은 기둥 (n-1개의 원판을 옮기기 위함)
     */
    void action(int n, int from, int to, int tmp) {
        // 남은 기둥이 1개면 옮기고 끝
        if (n == 1) {
            list.add(new int[] {from, to});
            return;
        }

        // n-1개의 원판을 중간 기둥으로 옮긴다
        action(n - 1, from, tmp, to);
        // n-1개 다 옮겼으니까 n번 원판을 from에서 to로 옮긴다
        list.add(new int[] {from, to});
        // 남은 원판을 to로 옮긴다
        action(n - 1, tmp, to, from);
    }
}
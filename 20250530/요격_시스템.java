import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        // e 를 기준으로 오름차순 정렬
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1]);

        int answer = 0;
        int pos = -1;
        for (int[] target : targets) {
            // 현재 위치에서 요격할 수 없으면 e 에서 요격
            if (pos < target[0]) {
                pos = target[1];
                answer++;
            }
        }

        return answer;
    }
}

import java.util.*;

/*
 * 1. 폭격 미사일의 구간을 끝점 기준으로 오름차순 정렬
 * 2. 현재 요격 미사일로 커버하지 못하는 구간이 나타나면,
 *    그 구간의 끝점에 다시 요격 미사일을 쏜다.
 */
class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        Arrays.sort(targets, (o1, o2) -> o1[1] - o2[1] ); //오름차순
        int missilePoint = -1;
        for (int[] target : targets) {
            if(missilePoint <= target[0]) {
                answer++;
                missilePoint = target[1];
            }

        }
        return answer;
    }
}
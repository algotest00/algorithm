import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        // 근무태도 점수 내림차순, 동료평가 점수 오름차순 정렬
        int[][] sortedScores = Arrays.stream(scores)
                .sorted((score1, score2) -> score1[0] - score2[0] != 0
                        ? -(score1[0] - score2[0])
                        : score1[1] - score2[1])
                .toArray(int[][]::new);

        // 인센티브 못 받는 사원 필터링
        List<int[]> filtered = new ArrayList<>();
        filtered.add(sortedScores[0]);
        int lastMaxB = sortedScores[0][1];
        for (int i = 1; i < sortedScores.length; i++) {
            if (sortedScores[i][1] >= lastMaxB) {
                lastMaxB = sort edScores[i][1];
                filtered.add(sortedScores[i]);
                continue;
            }

            // 인센 못받는 사원이 완호이면 종료..
            if (sortedScores[i].equals(scores[0])) return -1;
        }

        // 총점 내림차순 정렬
        filtered.sort(Comparator.comparing(score -> -(score[0] + score[1])));

        // 완호 등수 찾기 : 동석차는 건너뜀
        for (int i = 0; i < filtered.size(); i++) {
            int[] score = filtered.get(i);
            if (scores[0][0] + scores[0][1] == score[0] + score[1]) {
                return i + 1;
            }
        }

        return -1;
    }
}
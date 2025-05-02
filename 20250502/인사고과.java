import java.util.*;

// [지배정의]
// 어떤 사람의 점수 [a, b] 가 있을경우 scores 배열 중에 임의의 점수 중 a와 b보다 큰경우가 있으면, 이 사람은 인센티브를 받지 못한다.
// 즉 a는 내림차순으로, b는 오름차순으로 정렬해야한다.
// [석차 계산 로직]
// - 인센티브 대상자(validList)를 총점 기준으로 내림차순 정렬함
// - 이후 같은 총점을 가진 사람은 같은 등수를 유지하며, 다음 등수는 건너뜀 (동점자 처리 방식)
//   예: 총점 순서가 [200, 200, 190]이면 → 등수는 [1, 1, 3]
// - 완호의 점수가 언제 등장하는지를 체크해 해당 시점의 등수를 반환
// [중요 예시 설명]
// 예: scores = [[100, 90], [100, 80]]
// → 첫 번째 점수 a는 같고, b는 각각 90, 80
// → b도 내림차순 정렬할 경우: [100, 90]이 먼저 나오고 maxB가 90이 됨
// → 그 다음 [100, 80]은 b=80 < maxB=90 → 잘못 지배당했다고 판단될 수 있음 
// → 따라서 a가 같을 경우, b는 **오름차순**으로 정렬해야 위와 같은 잘못된 지배 판단을 피할 수 있음 

class Solution {
    public int solution(int[][] scores) {
        int[] whanHo = scores[0];

        /* 1.지배구조 로직 시작 */
        Arrays.sort(scores, (a, b) -> {
            if (a[0] == b[0]) return a[1] - b[1];
            return b[0] - a[0];
        });

        int maxB = -1;
        List<int[]> validList = new ArrayList<>();

        // 전제:
        // scores는 a 내림차순, a가 같다면 b 오름차순으로 정렬되어 있음
        // => 현재 score는 이전에 본 사람들보다 a가 작거나 같음이 보장됨
        // => 따라서 b만 비교하면 지배 여부를 판단할 수 있음
        for (int[] score : scores) {
            if (score[1] < maxB) {
                if (score == whanHo) return -1; 
                continue;
            } else {//
                maxB = Math.max(maxB, score[1]);
                validList.add(score);
            }
        }

        /* 2. 석차계산 로직 시작 */
        // 총점 기준 내림차순 정렬
        validList.sort((a, b) -> {
            int sumA = a[0] + a[1];
            int sumB = b[0] + b[1];
            return Integer.compare(sumB, sumA);
        });

        int rank = 1;
        int prevSum = -1;
        int sameRankCount = 0;

        for (int i = 0; i < validList.size(); i++) {
            int sum = validList.get(i)[0] + validList.get(i)[1];

            if (sum != prevSum) {
                rank += sameRankCount;
                sameRankCount = 1;
                prevSum = sum;
            } else {
                sameRankCount++;
            }

            if (Arrays.equals(validList.get(i), whanHo)) {
                return rank;
            }
        }

        return -1; // 이론상 도달하지 않음
    }
}

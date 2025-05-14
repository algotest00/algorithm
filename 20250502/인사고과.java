import java.util.*;

class Solution {
    // 어떤 직원 (a, b)가 있을 때, 자기보다 근무 태도 점수가 높은 직원 중 동료 평가 점수도 더 높은 직원이 존재하면 인센티브 못 받음
    // (즉, 한 명이라도 둘 다 자신보다 높은 사람이 있으면 탈락)
    public int solution(int[][] scores) {
        int[] wanhoScore = scores[0];
        int rank = 1;
        int maxScore = 0;
        int wTotal = wanhoScore[0] + wanhoScore[1];
            // 근무 태도 점수 내림차순 정렬b[0] - a[0]
            // 만약 같다면 동료 평가 점수 오름차순 정렬 (a[1] - b[1])
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);  
        
        for(int[] score : scores) {
            if(maxScore <= score[1]) { // 인센티브 자격 있음
                maxScore = score[1];
                if(score[0] + score[1] > wTotal) rank++;  // 완호 보다 총점 높으면 등수 밀림
            } else {
                if(score.equals(wanhoScore)) return -1; // 완호가 인센티브 못 받는 경우
            }
        }
        return rank;
    }
}
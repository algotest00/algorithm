import java.util.*;

class Solution {
    public String solution(String X, String Y) {
        int[] xCount = new int[10]; // X에서 숫자 0~9 등장 횟수
        int[] yCount = new int[10]; // Y에서 숫자 0~9 등장 횟수
        
         // X의 숫자 개수 카운트
        for (char c : X.toCharArray()) {
            xCount[c - '0']++;
        }
        
         // Y의 숫자 개수 카운트
        for (char c : Y.toCharArray()) {
            yCount[c - '0']++;
        }
          StringBuilder sb = new StringBuilder();

        // 9부터 0까지 공통 숫자를 찾아 최대 개수만큼 추가
        for (int i = 9; i >= 0; i--) {
            int commonCount = Math.min(xCount[i], yCount[i]); // 짝 지을 수 있는 개수
            sb.append(String.valueOf(i).repeat(commonCount));
        }

        // 결과 처리
        if (sb.length() == 0) return "-1";  // 짝꿍이 없으면 -1
        if (sb.charAt(0) == '0') return "0"; // 0인경우

        return sb.toString();
    }
}
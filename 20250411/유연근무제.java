import java.util.*;
// 금토일 월화 수목
// 5 6 7 1 2 3 4
// 0 1 2 3 4 5 6 idx+startday로 요일을 구할 수 있다.
// *** 실수포인트
// * 1. minute == 60 비교만 해서, 60 이상일 때 시간 보정 누락
// * 2. 시간 계산을 * 60이 아닌 * 100 단위로 계산해서 왜곡 발생
// * 3. 요일 계산을 day == 0/6으로만 체크함 → (j + startday) % 7 로 해야 정확
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int count = 0;
        for (int i = 0; i < timelogs.length; i++) {
            int hour = schedules[i]/100 * 100;
            int minute = schedules[i] % 100 + 10;
            int schedule = hour;
            schedule += (minute >= 60 ? 100 + minute % 60  : minute);
            for (int j = 0; j < timelogs[i].length; j++) {
                int day = j+startday;
                if(day % 7 == 6 || day % 7 == 0) {
                    continue;
                }
                if(timelogs[i][j] > schedule) {
                    count++;
                    break;
                }
            }
        }
        return schedules.length-count;
    }
}
import java.util.*;
class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        //timelogs[i]의 길이 = 7 : 7일간 이벤트 실행
        // 6시 ~ 23시 59분 출근 가능
        // 월~금(1~5), 토,일 제외 
        int n = schedules.length;
        
        for(int i = 0; i< n; i++){
           int hope_time = schedules[i];
        int end_time = time_set(hope_time);
        
            boolean allOkay = true;
          for (int j = 0; j < 7; j++) {
                int day = (startday + j - 1) % 7 + 1; // 1~7 

                if (day >= 1 && day <= 5) { // 월~금만
                    int actual_time = timelogs[i][j];
                    if (actual_time > end_time) {
                        allOkay = false; // 지각
                        break;
                    }
                }
            }

            if (allOkay) answer++; // 상품 지급
        }

        return answer;
    }

    
    public int time_set(int time){
        int hour = time / 100;
        int min = time%100;
        
        min = min+10;
        
        if(min>60){
            hour++;
            min= min -60;
        }
        return hour * 100 + min;

    }
}
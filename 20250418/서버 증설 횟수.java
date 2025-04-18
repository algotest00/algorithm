import java.util.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;
        int [] operatings = new int[24];
        for(int i = 0; i < players.length; i++){
            int needCnt = players[i] / m; // 해당 시간에 필요한 서버개수 
            int timeIdx = i; // 서버가 증설됐을때 시작점.
            int count = k;  //k자체를 빼면 다시 확인할 수 없으므로. count에다가 위임.
            
            //1. 추가해야하는 서버 = 필요한서버 - 현재운영중인 서버
            int addedCnt = needCnt - operatings[i];
            if(operatings[i] >= needCnt) continue; // => 현재운영중인 서버가 필요한 서버보다 많을땐 추가할 필요없음.
            
            //2. 서버가 증설한 지속시간만큼 유지시켜주기
            while(count > 0){
                operatings[timeIdx] += addedCnt;    
                timeIdx++;
                count--;
                if(timeIdx >= 24) break;
            }
            //3. 증설한 서버개수 더하기
            answer+= addedCnt;
        }
        
        return answer;
    }
}
// 선물 주고 받은 경우 잇을경우
// 선물 많이 준사람이 받음
// 선물지수 : 준선물 - 받은선물 
// 선물 주고받은 기록같거나 받은적 없을 경우 지수 크면 선물 받음

// 결과 : 선물 많이 받을 사람이 몇개 받을지
// gifts  : 선물 준사람 받은사람
import java.util.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int friendsLength = friends.length;
        
        // 빠르게 친구 찾기 위한 해시맵 생성
        HashMap<String, Integer> friendsMap = new HashMap<>();
        for(int i = 0; i < friendsLength; i++){
            friendsMap.put(friends[i], i);
        }
        
        // gifts 배열 돌면서 두 배열 생성(인덱스 : friendsMap의 value)
        // 1. giftDegree(선물지수) : 주면 +1, 받으면 -1
        // 2. giftGraph : 입출력 예제와 같은 형태의 2차원 배열
        int[] giftDegree = new int[friendsLength];
        int[][] giftGraph = new int[friendsLength][friendsLength];
        
        for(String gift : gifts){
            String[] giftName = gift.split(" "); // 공백 기준으로 분할
            giftDegree[friendsMap.get(giftName[0])]++; // 선물 준 것
            giftDegree[friendsMap.get(giftName[1])]--; // 선물 받은 것
            giftGraph[friendsMap.get(giftName[0])][friendsMap.get(giftName[1])]++;
        }
        
        int answer = 0;
        
        for(int i = 0; i < friendsLength; i++){
            int count = 0;
            for(int j = 0; j < friendsLength; j++){
                // 본인일 경우 다음 반복문
                if(i == j){
                    continue;
                }
                
                // 선물 준 적이 더 많거나 동일한 갯수로 주고 받았다면 선물지수가 더 높다면
                if(giftGraph[i][j] > giftGraph[j][i] || 
                  (giftGraph[i][j] == giftGraph[j][i] && giftDegree[i] > giftDegree[j])){
                    count++;
                }
                
                // 선물 지수 비교해서 높은 것을 저장
                if(answer < count){
                    answer = count;
                }
            }
        }
        
        return answer;
    }
}
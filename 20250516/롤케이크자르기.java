import java.util.HashMap;
import java.util.HashSet;

class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        
        // 왼쪽 종류 카운트를 위한 Set
        HashSet<Integer> leftSet = new HashSet<>();
        
        // 오른쪽 종류 카운트를 위한 Map
        HashMap<Integer, Integer> rightMap = new HashMap<>();
        for (int t : topping) {
            rightMap.put(t, rightMap.getOrDefault(t, 0) + 1);
        }

        // 오른쪽 조각의 종류 수
        int rightCount = rightMap.size();
        
        // 순회하면서 왼쪽과 오른쪽 비교
        for (int t : topping) {
            leftSet.add(t);
            
            // 오른쪽 Map에서 개수 줄이고 0이 되면 제거
            int currentCount = rightMap.get(t);
            if (currentCount == 1) {
                rightMap.remove(t);
            } else {
                rightMap.put(t, currentCount - 1);
            }
            
            // 사이즈(종류 수)가 같으면 경우의 수 추가
            if (leftSet.size() == rightMap.size()) {
                answer++;
            }
        }

        return answer;
    }
}
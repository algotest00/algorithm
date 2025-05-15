import java.util.*;

class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(5, new String[]{"Jeju", "Pangyo", "NewYork", "newyork"}));
    }

    public int solution(int cacheSize, String[] cities) {
        int answer = 0;

        LinkedHashMap<String, Boolean> cache = new LinkedHashMap<>(cacheSize, 0.75f, true) {
            // 언제 가장 오래된 값을 삭제할지 여부를 재정의
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > cacheSize;
            }
        };
        for (String cityStr : cities) {
            String city = cityStr.toUpperCase();
            if (cache.containsKey(city)) {
                answer++;
            } else {
                answer += 5;
            }
            cache.put(city, true);
        }

        return answer;
    }
}
import java.util.*;
/*
 * LRU알고리즘처리를 위한 자료구조 LinkedHashSet
 * 가장 최근에 사용된 데이터를 가장 뒤로 보내고, 오래된 데이터를 가장 앞으로 두어야 함.
 */
class Solution {
    public int solution(int cacheSize, String[] cities) {
        //0. 캐시사이즈가 없는경우에는 바로 리턴
        if (cacheSize == 0) return cities.length * 5; 
        
        //1. 있는경우 LRU에 따라 캐싱수행
        int answer = 0;
        LinkedHashSet<String> cache = new LinkedHashSet<>(cacheSize);

        for (String name : cities) {
            String city = name.toUpperCase();

            if (cache.contains(city)) {
                answer += 1;
                //2. 이전에 넣은거 최신으로 갱신.
                cache.remove(city);
                cache.add(city);
            } else {
                answer += 5;
                //3. 캐시 사이즈유지를 위해 오래된 데이터 제거거
                if (cache.size() >= cacheSize && !cache.isEmpty()) {
                    Iterator<String> it = cache.iterator();
                    if (it.hasNext()) {
                        it.next();
                        it.remove();
                    }
                }
                cache.add(city);
            }
        }
        
        return answer;
    }
}
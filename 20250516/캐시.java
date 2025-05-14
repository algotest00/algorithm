import java.util.*;


class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        
        if(cacheSize == 0){
            return cities.length * 5;
        }
        
        List<String> cache = new LinkedList<String>();
        
        for(String city : cities){
            city = city.toLowerCase();
            Boolean isHit = false;
            for(int i = 0; i < cache.size(); i++){
                if(cache.get(i).equals(city)){
                    cache.remove(i);
                    cache.add(0, city);
                    isHit = true;
                    answer += 1;
                    break;
                }
            }
            if(!isHit){
                if(cache.size() == cacheSize){
                    cache.remove(cacheSize - 1);
                }
                cache.add(0, city);
                answer += 5;
            }
        }
        
        return answer;
    }
}
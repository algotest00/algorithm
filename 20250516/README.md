# 5월 3주차 문제
1. [[1차]캐시](https://school.programmers.co.kr/learn/courses/30/lessons/17680)
2. [롤케이크 자르기](https://school.programmers.co.kr/learn/courses/30/lessons/132265)


## [[1차]캐시](https://school.programmers.co.kr/learn/courses/30/lessons/17680)
- LRU 구현 문제
- LRU 가장 오래전에 사용된 데이터를 교체하는 알고리즘
- LocalDateTime 구현 -> 채점 11번 케이스 실패;;;;;
```java
import java.time.*;
import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Map<String, Long> cache = new HashMap<>();

        for (String cityStr : cities) {
            String city = cityStr.toLowerCase();

            // 캐시에 있으면
            if (cache.containsKey(city)) {
                cache.compute(city, (key, value) -> System.currentTimeMillis());
                answer++;
                continue;
            }

            // 캐시 용량 다 찼으면
            if (cache.size() >= cacheSize) {
                // 가장 오래 사용안한 데이터 제거
                cache.entrySet().stream()
                        .min(Map.Entry.comparingByValue())
                        .ifPresent(e -> cache.remove(e.getKey()));
            }

            if (cacheSize - cache.size() > 0) cache.put(city, System.currentTimeMillis());
            answer += 5;
        }

        return answer;
    }
}
```

## LinkedHashMap 사용
- 요소 접근시 accessOrder ture 이면 재정렬함
```java
    public V get(Object key) {
        Node<K,V> e;
        if ((e = getNode(key)) == null)
            return null;
        if (accessOrder)
            afterNodeAccess(e);
        return e.value;
    }
```
- - 접근한 요소의 after 값을 head 에 담음
- 가장 최근에 사용한 요소 다음 값은 가장 오래전에 사용된 요소임 (doubly linked list)
```java
    void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;
        if (accessOrder && (last = tail) != e) {
            LinkedHashMap.Entry<K,V> p =
                (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
            p.after = null;
            if (b == null)
                head = a;
            else
                b.after = a;
            if (a != null)
                a.before = b;
            else
                last = b;
            if (last == null)
                head = p;
            else {
                p.before = last;
                last.after = p;
            }
            tail = p;
            ++modCount;
        }
    }
```
- put -> 노드 linking
```java
    // link at the end of list
    private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
        LinkedHashMap.Entry<K,V> last = tail;
        tail = p;
        if (last == null)
            head = p;
        else {
            p.before = last;
            last.after = p;
        }
    }
```
- 노드 삽입 이후 가장 오래된 요소(head)를 삭제
```java
    void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        if (evict && (first = head) != null && removeEldestEntry(first)) {
            K key = first.key;
            removeNode(hash(key), key, null, false, true);
        }
    }
```





---
## [롤케이크 자르기](https://school.programmers.co.kr/learn/courses/30/lessons/132265)
- 1 <= topping.length <= 1_000_000
- 1 <= topping[i] <= 10_000
- 종류 개수가 동일하게 slice하는 경우의 수?
- Map에 토핑 개수 체크
- Map에서 하나씩 꺼내 Set으로 옮김
  - Map의 value == 0 되면 key 제거
- Map과 Set size가 같아지면 경우의 수 + 1
- Map보다 Set이 커지면 종료


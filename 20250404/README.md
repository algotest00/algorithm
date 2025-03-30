# 4월 1주차 문제
- [완전범죄](https://school.programmers.co.kr/learn/courses/30/lessons/389480)
- [가장 많이 받은 선물](https://school.programmers.co.kr/learn/courses/30/lessons/258712)


## 완전범죄
- 모든 물건을 훔쳐야 함
- A 도둑의 흔적 누적 개수의 합 중 최소값
- 완전탐색 ?
  - 물건 최대 갯수 40
  - 도둑 2
  - 모든 경우의 수 탐색 : 2^40 => timeout
- 물건마다 모든 경우의 수에 대한 값을 저장 ?
  - i번째 물건의 B의 누적합 경우의 수별로 A의 누적합 최소값을 저장
  - DP
  - `dp[i][b]` : i번째 물건의 B 누적합 경우의 수(b)의 A 누적합 최소값
    - A가 훔친 경우와 B가 훔친 경우 중 더 작은 수를 저장
    - A가 훔친 경우(`dp[i][b]`) : 이전 물건의 경우(`dp[i-1][b]`) + `info[i][b]`
    - B가 훔친 경우(`dp[i][b + info[i][1]]`) = `dp[i - 1][j]`



```java
// CASE 고민없이 그냥 A 최소값으로만 빼줘서 틀림
import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        int a = 0;
        int b = Arrays.stream(info).mapToInt(o -> o[1]).sum();
        if (b < m) return a;
        
        int[][] sortedInfo = Arrays.stream(info).sorted(Comparator.comparingInt(o -> o[0])).toArray(int[][]::new);
        for (int[] arr : sortedInfo) {
            a += arr[0];
            b -= arr[1];
            
            if (a < n && b < m) return a;
        }
        
        return -1;
    }
}
```



## 가장 많이 받은 선물
- 친구끼리 선물 보낸 히스토리 만들어두고
- 전체 친구들 1:1 조합 = 50_C_2 => 시간복잡도 신경안써도 될 듯

- 자료구조 생성
  - 친구에게 선물 보낸 횟수
  - 친구에게 선물 받은 횟수
  - 선물지수
- o1, o2 누가 선물을 보낼지 결정하는 로직
  - 1:1 비교만 하면 되니까 Comparable 구현하여 비교로직 작성

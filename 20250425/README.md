# 4월 4주차 문제
- [무인도 여행](https://school.programmers.co.kr/learn/courses/30/lessons/154540)
- [하노이의 탑](https://school.programmers.co.kr/learn/courses/30/lessons/12946)


## [무인도 여행](https://school.programmers.co.kr/learn/courses/30/lessons/154540)
```
X591X
X1X5X
X231X
1XXX1
```
- 1,2,3 행 무인도는 연결되어있음 => 합 27
- 4 행 무인도 2개는 연결되어있지 않음 => 1, 1
- 오름차순 [1, 1, 27]

```
XXX
XXX
XXX
```
- 섬이 없음. -1

### 풀이
- DFS
- DFS 하면서 갈 수 있는 땅은 방문 처리
- 방문한 땅의 식량을 합산




## [하노이의 탑](https://school.programmers.co.kr/learn/courses/30/lessons/12946)
- 기둥 1, 2, 3이 있음 
- N개의 원판을 1번에서 3번으로 보내야 함
- N-1개의 원판은 남은 기둥인 2번으로 보내야 함
- N-1개를 1번에서 2번으로 보내려면? N-2개의 원판은 3번으로 보내야 함
- 이런식으로 N-1개를 다 보냈으면, N번 원판을 3번으로 보냄
- 이제 N-1개의 원판은 2번에 있음. 3번으로 보내야 함
- 그럼 또 N-2개의 원판을 2번에서 1번으로 보내야 N-1번 원판을 2번에서 3번으로 보내겠지?
- 반복...
- 요약
  1. n개의 원판을 from에서 to로 보내고 싶음
    - n이 1이면 옮기고 종료 
  2. 먼저 n-1개를 from에서 tmp(남은기둥)로 보냄 (1번으로 돌아감. n:n-1, to:tmp)
  2. n번 기둥을 from에서 to로 보냄
  3. 이제 n-1개의 원판은 tmp에 있음. tmp에서 to로 보냄 (1번으로 돌아감. n:n-1, from:tmp)


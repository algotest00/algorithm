# 4월 2주차 문제
- [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165)
- [서버 증설 횟수](https://school.programmers.co.kr/learn/courses/30/lessons/389479)



## [타겟 넘버](https://school.programmers.co.kr/learn/courses/30/lessons/43165)
### 주어지는 것
- numbers : 숫자 배열
  - 2 <= numbers.length <= 20
  - 1 <= numbers[i] <= 50
- target : 타겟 넘버
  - 1 <= target <= 1_000

### 풀이
- `numbers[i]` 를 더하거나 빼서 `target`이 나오는 경우의 수를 골라야 함
- 완전 탐색 ?
- 2^20 = 1_048_576
- 각 수의 덧셈, 뺄셈 모든 경우의 수를 해도 100만번 정도. 재귀로 끝까지 탐색



## [서버 증설 횟수](https://school.programmers.co.kr/learn/courses/30/lessons/389479)
- **최소 증설 횟수 구하기**
- `m`명의 
- 서버 1대당 `m - 1`명의 이용자 가능
- `n * m` <= 이용자 수 < `(n + 1) * m` : `n`대 증설 필요
- 증설된 서버는 `k` 시간만큼 운영
- `payers` : 특정 시간대의 이용자 수를 담은 배열
  - `players[i]` : i시 ~ i+1시 사이의 게임 이용자의 수
  - 0 <= `players[i] `<= 1_000
- 1 ≤ `m` ≤ 1,000
- 1 ≤ `k` ≤ 24

### 풀이
1. 증설된 서버를 담는 큐를 만들어두고, 매 시간 시작마다 큐 확인해서 운영 종료 처리하는 방법
   - 시간당 최대 증설 횟수 : `players[i] / m`
   - 최악의 경우 `players[i] / m`는 1,000 -> 큐 확인을 시간당 1,000번 하므로 총 반복횟수 24,000 : 충분
   - 특이 케이스가 있을까?
   - 딱히 없을 것 같다.. 구현




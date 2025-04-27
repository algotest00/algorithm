# 5월 1주차 문제
2. [인사고과](https://school.programmers.co.kr/learn/courses/30/lessons/152995)
2. [파괴되지 않은 건물](https://school.programmers.co.kr/learn/courses/30/lessons/92344)


## [인사고과](https://school.programmers.co.kr/learn/courses/30/lessons/152995)
완호네 회사는 연말마다 1년 간의 인사고과에 따라 인센티브를 지급합니다.
각 사원마다 근무 태도 점수와 동료 평가 점수가 기록되어 있는데 만약 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 그 사원은 인센티브를 받지 못합니다.
그렇지 않은 사원들에 대해서는 두 점수의 합이 높은 순으로 석차를 내어 석차에 따라 인센티브가 차등 지급됩니다.
이때, 두 점수의 합이 동일한 사원들은 동석차이며, 동석차의 수만큼 다음 석차는 건너 뜁니다.
예를 들어 점수의 합이 가장 큰 사원이 2명이라면 1등이 2명이고 2등 없이 다음 석차는 3등부터입니다.

사원수 100,000
완호 첫번째

10_000_000_000

### 풀이
- 인센티브를 받지 못하는 사원을 필터링하는 것이 관건
- 사원마다 전체 사원 다 확인하면 최악의 경우 n^2 = 10억 -> 너무 느림 
- 근무태도(a) 내림차순, 동료평가(b) 오름차순 정렬..?
  - 임의 k가 성과평가 대상이려면? 이전 대상의 a는 동일하거나 무조건 큼. b가 같거나 더 커야함
  - b가 더 작다면, 인센 대상 X




## [파괴되지 않은 건물](https://school.programmers.co.kr/learn/courses/30/lessons/92344)
- 효율성 전체 탈락;
- 최악의 경우 1_000 * 1_000 * 250_000 : 얼마냐이게;;
```java
class Solution {
    public int solution(int[][] board, int[][] skill) {
        for (int i = 0; i < skill.length; i++) {
            int type = skill[i][0];
            if (type == 1) attack(board, skill[i][1], skill[i][2], skill[i][3], skill[i][4], skill[i][5]);
            if (type == 2) heal(board, skill[i][1], skill[i][2], skill[i][3], skill[i][4], skill[i][5]);
        }
        
        int answer = 0;
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (board[r][c] > 0) answer++;
            }
        }
        
        return answer;
    }
    
    private void attack(int[][] board, int r1, int c1, int r2, int c2, int amount) {
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                board[r][c] -= amount;
            }
        }
    }
    
    private void heal(int[][] board, int r1, int c1, int r2, int c2, int amount) {
        for (int r = r1; r <= r2; r++) {
            for (int c = c1; c <= c2; c++) {
                board[r][c] += amount;
            }
        }
    }
}
```


- 스킬 전체 합산해서 한 번에 적용할 수 있으면?
  - 시간복잡도가 확 줄어듦
  - 스킬 전체 탐색 : 250_000
  - board 합산 처리 : 1_000 * 1_000
  - 여기까진 생각했으나 구현을 못했음...
- 누적합 이용
  - col 1 -> c로 이동하며 합산 (`arr[r][c + 1] += arr[r][c]`)
  - row 1 -> r로 이동하며 합산 (`arr[r + 1][c] += arr[r][c]`)
- 누적합으로 계산된 스킬 내역 합산을 `board`에 적용


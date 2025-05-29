# 5월 4주차 문제
1. [순위](https://school.programmers.co.kr/learn/courses/30/lessons/49191?language=java)
2[요격 시스템](https://school.programmers.co.kr/learn/courses/30/lessons/181188?language=java)

## [순위](https://school.programmers.co.kr/learn/courses/30/lessons/49191?language=java)
- 모순이 없으니까, 각 선수에 대해 경기 횟수를 기록만 하면 된다.
- 경기 횟수가 `n - 1` && 모든 선수와 경기 진행 - 직접적으로 하지 않아도 됌
- DFS : 모든 경기기록을 확인하여 A 선수가 이긴 선수 수, 진 선수 수 체크
- 시간복잡도 O(n^2)


---
## [요격 시스템](https://school.programmers.co.kr/learn/courses/30/lessons/181188?language=java)
- 미사일을 최소로 사용
- `e` 기준으로 오름차순 정렬
- 포격마다 반복
  - 미사일 장전, 현재 위치에서 요격 가능하면(현재 조준 위치가 폭격 범위 내이면) 다음 포격 탐색
  - 현재 위치에서 요격 불가능하면(현재 조준 위치가 포격 범위 밖이면, `s` 보다 작으면 포격 범위 밖임), 현재 포격 끝지점(`e`)에서 요격
  - 그러면 효율적으로 사용할 수 있음


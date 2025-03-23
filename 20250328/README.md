# 3월 4주차 문제
- [지게차와 크레인](https://school.programmers.co.kr/learn/courses/30/lessons/388353)
- [비밀 코드 해독](https://school.programmers.co.kr/learn/courses/30/lessons/388352)


## 지게차와 크레인
- storage 최대 크기 : 50 * 50 => 2500
- requests 최대 갯수 : 100
- 칸 제거 로직
  - when : requests[i].length == 1, then : 해당 칸에서 밖으로 나갈 수 있는지 확인
    - BFS
  - when : requests[i].length == 2, then : replaceAll
- 제거칸 buffer에 모아두고 한 번에 제거
  - 즉시 제거하면 제거되지 말아야 할 칸도 제거될 수 있음

=> DFS, BFS 로직 까먹어서 오래걸림;;


## 비밀 코드 해독
- n : 코드값 max = 30
- q.length == ans.length == m, max = 10
- q[i] : 입력 숫자 5개
- ans[i] : q[i]의 유효 숫자 수
- 가능한 숫자 조합중 ans 결과를 모두 만족하는 조합의 수를 구해야 함
- 30개 숫자 중 5개 조합 : 각 조합에 대해 m번 반복해서 case 확인해도 timeout 안날듯
- 로직 : {1,2,3,4,5} 부터 {n-4, n-3, n-2, n-1, n} 까지 조합 만들고, case 확인 (q, ans 일치하는지)

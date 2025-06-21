# 6월 2주차 문제
https://github.com/tony9402/baekjoon/tree/main/algorithms/data_structure
- [백준 1068번. 트리](https://www.acmicpc.net/problem/1068)
- [백준 17073번. 나무 위의 빗물](https://www.acmicpc.net/problem/17073)



## [백준 1068번. 트리](https://www.acmicpc.net/problem/1068)
- 입력값 트리로 만들고
- 특정 노드 제거
- 리프 노드 개수?
- 제거시, 하위 노드 모두 제거해야함 : 재귀


## [백준 17073번. 나무 위의 빗물](https://www.acmicpc.net/problem/17073)
- N 개의 노드
- 루트에 W 만큼 물이 있음
- 매 초마다 반복
  - if 물 != null and hasChildren then sendWater2RandomChild 
  - 부모에게서 물 받기
- P(i) = i 노드의 물의 양
- P(i) > 0 의 평균값?
- 모든 물은 리프 노드에 적재
- 어렵게 적어놨는데 그냥 W / 리프count
- DFS 로 노드 방문하면서 리프노드인지 체크 : 시간초과........................................
- 그냥 간선 1개인 노드가 리프노드임. 왜 굳이 DFS? -> 반성하자 ㅋㅋ


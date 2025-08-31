# 8월 5주차 문제

> https://github.com/tony9402/baekjoon/tree/main/algorithms/tree

- [백준 15900번. 나무 탈출](https://www.acmicpc.net/problem/15900)
- [백준 19641번. 중첩 집합 모델](https://www.acmicpc.net/problem/19641)


---

## [백준 15900번. 나무 탈출](https://www.acmicpc.net/problem/15900)
- 마지막으로 루트로 옮길 수 있으면 승리
- 리프 노드의 깊이 합이 짝수이면 못이김
- 리프노드 찾기가 관건
- 1에서부터 타고가면서 노드에서 갈 수 있는 길이 부모밖에 없으면 리프노드임.
- 리프 노드의 깊이 합을 구하면 된다.
- (출력값 대소문자 오류...............)

---

## [백준 19641번. 중첩 집합 모델](https://www.acmicpc.net/problem/19641)
- 루트의 left, right 는 1, (하위 노드들 개수 * 2 + 2)
- 번호가 가장 낮은 노드부터 오름차순으로 방문
- 예제에서
  - 루트 노드 (2)의 left, right 는 1, 6
  - 1번 노드의 left, right 는 2, 3
  - 3번 노드의 left, right 는 4, 5
- 탐색 로직
  - 현재 노드의 left 입력
  - 하위 노드가 있으면 탐색
  - 현재 노드의 right 입력
- 알고리즘 속도 개선 : primitive 타입 사용

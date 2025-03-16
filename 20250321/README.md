# 02주차 문제
1. [신고 결과 받기](https://school.programmers.co.kr/learn/courses/30/lessons/92334)
2. [개인정보 수집 유효기간](https://school.programmers.co.kr/learn/courses/30/lessons/150370)

---

## 신고 결과 받기
### 풀이
1. `report`를 통해 유저가 신고**한** 목록(Set)을 만들고
2. `report`를 통해 유저별 신고**받은** 목록(Set)을 만든다.
3. `user_id`를 돌면서 특정 유저가 신고한 유저들이 정지당했는지 확인
   - (2)의 `Set.size()`가 유저가 신고당한 횟수임. 이를 확인
4. 지정값(`k`)보다 많이 신고당했으면 정지 -> 카운팅한다.

---

## 개인정보 수집 유효기간
- today YYYY.MM.DD
- terms ["A 3", "B 100", ...]
- privacies ["YYYY.MM.DD A", "YYYY.MM.DD B", ...]


### 풀이
1. terms >> `Map<종류, 유효기간>`로 변환
2. privacies >> `List<종료일자>`로 변환
3. `List<종료일자>.filter(exp -> today > exp)` 갯수 반환


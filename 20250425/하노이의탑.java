/**
 * 풀이실패.. 알고리즘 참조.
 * 하노이의 탑 알고리즘
 * 
 * 1. 원판이 1개일 경우:
 *    - 출발 기둥에서 도착 기둥으로 바로 이동한다.
 * 
 * 2. 원판이 n개일 경우:
 *    - 위의 n-1개 원판을 보조 기둥으로 이동
 *    - 가장 큰 원판(맨 아래)을 도착 기둥으로 이동
 *    - 보조 기둥에 있는 n-1개 원판을 도착 기둥으로 이동
 */

 import java.util.ArrayList;
 import java.util.List;
 
 class Solution {
 
     private final List<int[]> moves = new ArrayList<>();
 
     public int[][] solution(int n) {
         hanoi(n, 1, 3, 2);
         int[][] result = new int[moves.size()][2];
         for (int i = 0; i < moves.size(); i++) {
             result[i] = moves.get(i);
         }
         return result;
     }
 
     private void hanoi(int n, int from, int to, int via) {
         if (n == 1) {
             moves.add(new int[]{from, to});
             return;
         }
         hanoi(n - 1, from, via, to);
         moves.add(new int[]{from, to});
         hanoi(n - 1, via, to, from);
     }
 }
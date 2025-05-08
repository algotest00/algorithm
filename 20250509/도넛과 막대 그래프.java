/*
    풀이실패 (정답출처 https://jangyoujung.com/98)
    각 그래프의 개수를 세는 방법에서 틀림. 
    //최초 dfs로 연결된노드를 어떻게 구분짓지 -> 불가
    
    //생성한 정점 -> 나가는 간선이 2개 이상이고, 들어오는 간선이 없을 경우
    
    ////그래프는 연결된 노드중 핵심노드에 대해서만 카운트하면됨. 
    // 막대랑, 8자만 규칙을 찾고 도넛은 나머지로 계산... 다시풀어도 이렇게 생각할 자신이 없음.

    //막대그래프 -> 나가는 간선이 없고, 들어오는 간선이 있을 경우 
    //8자그래프 -> 나가는 간선 두개이상, 들어오는 간선 두개이상
    //도넛그래프 -> 정점에서 뻗어나온 간선 - 막대그래프 - 8자그래프
    
    from과 to를 구하는 자료구조를 생각 못함. -> Map<Integer, int[]> nodes // int[] => size = 2
    0번째는 from으로 시작한 개수, 1번째는 to로 지명된 개수

*/

import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
    
        Map<Integer, int[]> nodes = new HashMap<>();  // 노드의 from과 to 개수 구하기
        
        int extraNode = -1; // 추가된 노드
        int doughnut = 0; // 도넛 그래프 개수
        int stick = 0;	// 막대 그래프 개수
        int figure8 = 0;  // 8자 그래프 개수


	    // 1단계: 각 노드의 간선 개수 계산
        for(int[] edge: edges){
            int from = edge[0];
            int to = edge[1];

            if(!nodes.containsKey(from)){
                nodes.put(from, new int[]{0,0});
            }
            if(!nodes.containsKey(to)){
                nodes.put(to, new int[]{0,0});
            }
          
            nodes.get(from)[0]++;
            nodes.get(to)[1]++;
        }

        // 2단계: 노드를 탐색하며 각 그래프의 '핵심 노드' 찾으면 개수 갱신
        for(int key : nodes.keySet()){
            int[] count = nodes.get(key);
            // 생성한 정점 from >= 2 and to = 0
            if(count[0] >= 2 && count[1] == 0) {
                extraNode = key;
            }
            
            // = 막대 그래프 from = 0 and to > 0
            else if(count[0] == 0 && count[1] > 0) {
                stick++;
            }
            // 들어오는 것과 나가는 것이 각 2개 이상일 경우
            // = 8자 그래프
            else if(count[0] >= 2 && count[1] >= 2) {
                figure8++;
            }
        }

        // 추가한 노드에서 나오는 정점의 개수 = 전체 그래프의 수
        // 전체 그래프의 수에서 stick, figure8 빼면 도넛 그래프의 수
        doughnut = nodes.get(extraNode)[0] - stick - figure8;

        return new int[]{ extraNode, doughnut, stick, figure8 };

    }
}
import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, Integer> out = new HashMap<>();
        Map<Integer, Integer> in = new HashMap<>();
        int[] answer = new int[4];

        for (int[] edge : edges) { 
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }

        // 나가는게 2개 이상일경우 && in 이 0개 = 정점
        // 나가는게 2개 이상 && in이 1개이상 = 
        // 이 정점에서 뻗어나가는 간선이 몇 개인지 보고
        // 끝나는 곳이면 → 막대 그래프
        // 다시 자기 자신으로 돌아오면 → 도넛
        //두 개 이상이 연결되어 있으면 → 8자 모양
        for (int node : out.keySet()) {
            if (out.get(node) > 1) { // (2)
                if (!in.containsKey(node)) {
                    // 정점
                    answer[0] = node;
                } else {
                    // 8자
                    answer[3] += 1;
                }
            }
        }

        for (int node : in.keySet()) {
            // in 은 있으나 out 없음 = 막대그래프
            if (!out.containsKey(node)) { 
                answer[2] += 1;
            }
        }
        //도넛 개수 = 전체 가지 수 - 막대 수 - 8자 수
        answer[1] = out.get(answer[0]) - answer[2] - answer[3]; // (4)
        return answer;
    }
}
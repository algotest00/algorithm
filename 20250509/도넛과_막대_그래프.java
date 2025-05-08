import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> startCounts = new HashMap<>();
        Map<Integer, Integer> endCounts = new HashMap<>();

        // - 그래프를 만들면서 각 정점에 연결된 간선 수를 파악
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];

            // 그래프 세팅
            graph.putIfAbsent(start, new ArrayList<>());
            graph.get(start).add(end);

            startCounts.put(start, startCounts.getOrDefault(start, 0) + 1);
            endCounts.put(end, endCounts.getOrDefault(end, 0) + 1);
        }

        // 한 번도 end로 값이 안들어온 정점이 새로 만든 정점 k
        for (Integer key : startCounts.keySet()) {
            if (startCounts.get(key) > 1 && !endCounts.containsKey(key) ) {
                answer[0] = key;
                break;
            }
        }

        // - k 노드에서 각 노드 탐색 시작
        for (Integer i : graph.get(answer[0])) {
            // 정점에 연결된 간선이 1개 초과이면? 8자
            // start 정점으로 돌아왔으면? 도넛
            // 더이상 갈 정점이 없으면? 막대
            GraphType graphType = getGraphType(graph, i);
            if (graphType == GraphType.DONUT) answer[1]++;
            if (graphType == GraphType.STRAIGHT) answer[2]++;
            if (graphType == GraphType.Eights) answer[3]++;
        }

        return answer;
    }

    GraphType getGraphType(Map<Integer, List<Integer>> graph, int node) {
        int i = node;
        while (true) {
            List<Integer> nodes = graph.get(i);
            if (nodes == null) return GraphType.STRAIGHT;

            if (nodes.size() > 1) return GraphType.Eights;

            i = nodes.get(0);
            if (i == node) return GraphType.DONUT;
        }
    }

    enum GraphType {
        DONUT,
        STRAIGHT,
        Eights,
    }
}
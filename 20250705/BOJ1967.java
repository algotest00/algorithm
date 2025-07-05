import java.io.*;
import java.util.*;

public class BOJ1967 {
    static List<Edge>[] adj;
    static boolean[]    visited;
    static int          farthestNode, maxDistance;

    /**
     * 간선 데이터 클래스
     * to: 도착 노드 번호
     * weight: 가중치
     */
    static class Edge {
        int to, weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 노드 수
        int n = Integer.parseInt(br.readLine().trim());
        // 간선 배열 초기화
        adj = new List[n + 1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();

        // 간선 정보 입력
        for (int i = 0; i < n - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 부모노드
            int a = Integer.parseInt(st.nextToken());
            // 자식노드
            int b = Integer.parseInt(st.nextToken());
            // 가중치
            int w = Integer.parseInt(st.nextToken());

            adj[a].add(new Edge(b, w));
            adj[b].add(new Edge(a, w));
        }

        if (n == 1) {
            System.out.println(0);
            return;
        }

        // 1번 노드에서 가장 거리가 먼 노드 탐색
        visited = new boolean[n + 1];
        maxDistance = 0;
        dfs(1, 0);

        // 찾은 노드에서 다시 가장 먼 거리의 노드 탐색
        visited = new boolean[n + 1];
        maxDistance = 0;
        dfs(farthestNode, 0);

        System.out.println(maxDistance);
    }

    /**
     * 입력 node 에서 가장 거리가 먼 노드 위치 및 거리 갱신
     * @param node 탐색할 노드 번호
     * @param distance 이동한 거리
     */
    static void dfs(int node, int distance) {
        visited[node] = true;

        if (distance > maxDistance) {
            maxDistance = distance;
            farthestNode = node;
        }

        // 노드의 간선 탐색
        for (Edge e : adj[node]) {
            if (!visited[e.to]) {
                dfs(e.to, distance + e.weight);
            }
        }
    }
}
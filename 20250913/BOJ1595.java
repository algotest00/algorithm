import java.io.*;
import java.util.*;

public class BOJ1595 {
    static int N = 10000;
    static List<Edge>[] adj = new ArrayList[N + 1];
    static boolean[]    visited;
    static int          farthestNode;
    static long         maxDistance;

    /**
     * 간선 데이터 클래스<br/>
     * to: 도착 노드 번호<br/>
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
        for (int i = 0; i <= N; i++) adj[i] = new ArrayList<>();

        String line;
        while ((line = br.readLine()) != null && !line.isEmpty()) {
            StringTokenizer st = new StringTokenizer(line);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adj[a].add(new Edge(b, w));
            adj[b].add(new Edge(a, w));
        }

        // 1번 노드에서 가장 거리가 먼 노드 탐색
        visited = new boolean[N + 1];
        maxDistance = 0;
        dfs(1, 0);

        // 찾은 노드에서 다시 가장 먼 거리의 노드 탐색
        visited = new boolean[N + 1];
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
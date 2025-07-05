package 20250705;

import java.io.*;
import java.util.*;

/*
 * 트리의 지름 알고리즘 요약
 * - 임의 노드(1번)에서 가장 먼 노드 A를 찾는다.
 * - 노드 A에서 다시 가장 먼 노드 B를 찾는다.
 * - A-B 거리 = 트리의 지름.
 */
public class Main {
    static int N;
    static List<Edge>[] adj;
    static boolean[] visited;
    static int farthestNode, maxDist;

    static class Edge {
        int to, cost;
        Edge(int t, int c) {
            to = t;
            cost = c;
        }
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try { st = new StringTokenizer(br.readLine()); }
                catch (IOException e) { e.printStackTrace(); }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }

    static void dfs(int u, int dist) {
        visited[u] = true;
        if (dist > maxDist) {
            maxDist = dist;
            farthestNode = u;
        }

        for (Edge e : adj[u]) {
            if (!visited[e.to]) {
                dfs(e.to, dist + e.cost);
            }
        }
    }

    public static void main(String[] args) {
        FastReader fr = new FastReader();
        N = fr.nextInt();

        adj = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < N - 1; i++) {
            int p = fr.nextInt();
            int c = fr.nextInt();
            int w = fr.nextInt();
            adj[p].add(new Edge(c, w));
            adj[c].add(new Edge(p, w));
        }

        // 임의 노드(1번)에서 가장 먼 노드 A를 찾는다
        visited = new boolean[N + 1];
        maxDist = 0;
        dfs(1, 0);

        // 노드 A에서 다시 가장 먼 노드 B를 찾아서
        visited = new boolean[N + 1];
        maxDist = 0;
        dfs(farthestNode, 0);

        // A-B 거리 = 트리의 지름
        System.out.println(maxDist);
    }
}

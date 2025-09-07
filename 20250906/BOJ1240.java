import java.io.*;
import java.util.*;

public class BOJ1240 {

    static List<Edge>[] tree;
    static int target;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 노드 개수
        int M = Integer.parseInt(st.nextToken()); // 노드 쌍 개수

        tree = new List[N + 1];
        for (int i = 1; i < N + 1; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            tree[n1].add(new Edge(n2, d));
            tree[n2].add(new Edge(n1, d));
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());

            answer = 0;
            dfs(from, 0, 0);
            sb.append(answer).append('\n');
        }

        System.out.print(sb);
    }

    static void dfs(int current, int parent, int distance) {
        if (current == target) {
            answer = distance;
            return;
        }

        for (Edge e : tree[current]) {
            if (e.to == parent) continue;
            dfs(e.to, current, distance + e.distance);
        }
    }

    static class Edge {
        int to;
        int distance;

        public Edge(int to, int distance) {
            this.to = to;
            this.distance = distance;
        }
    }
}

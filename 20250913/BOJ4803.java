import java.io.*;
import java.util.*;

public class BOJ4803 {
    static List<Integer>[] graph;
    static boolean[]       visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int testCase = 1;

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            // 입력 0 0 이면 종료
            if (n == 0 && m == 0) break;

            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
            }

            visited = new boolean[n + 1];
            int treeCount = 0;

            for (int i = 1; i <= n; i++) {
                if (!visited[i] && dfs(i, 0)) treeCount++;
            }

            String result;
            if (treeCount == 0) result = "No trees";
            else if (treeCount == 1) result = "There is one tree";
            else result = String.format("A forest of %d trees", treeCount);

            sb.append(String.format("Case %d: %s.", testCase++, result)).append("\n");
        }

        System.out.print(sb);
    }

    static boolean dfs(int u, int parent) {
        visited[u] = true;
        for (int node : graph[u]) {
            if (!visited[node]) {
                if (!dfs(node, u)) return false;
                continue;
            }

            // 부모가 아닌데 이미 방문한 노드이면 트리가 아님
            if (node != parent) return false;
        }

        return true;
    }
}
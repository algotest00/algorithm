import java.io.*;
import java.util.*;

// time : 772ms
public class BOJ19641_2 {

    static int current = 1;
    static List<Integer>[] graph;
    static int[][] nodes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) graph[i] = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            while (st.hasMoreTokens()) {
                int b = Integer.parseInt(st.nextToken());
                if (b == -1) break;
                graph[a].add(b);
            }
        }

        // 오름차순 정렬
        for (int i = 1; i <= N; i++) {
            Collections.sort(graph[i]);
        }

        int S = Integer.parseInt(br.readLine());

        nodes = new int[N + 1][2];

        search(S, -1);

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            sb.append(i).append(" ").append(nodes[i][0]).append(" ").append(nodes[i][1]).append("\n");
        }
        System.out.print(sb);
    }

    static void search(int node, int parent) {
        nodes[node][0] = current++;

        for (int child : graph[node]) {
            if (child == parent) continue;
            search(child, node);
        }

        nodes[node][1] = current++;
    }
}

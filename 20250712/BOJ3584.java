import java.io.*;
import java.util.*;

public class BOJ3584 {
    static int[]     parent;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 테스트 케이스 개수
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            // 노드의 수
            int n = Integer.parseInt(br.readLine());
            parent = new int[n + 1];
            visited = new boolean[n + 1];

            for (int k = 0; k < n - 1; k++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                // a 는 b 의 부모임
                parent[b] = a;
            }

            // 테스트 케이스
            StringTokenizer st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            // node1 의 모든 조상 방문
            while (node1 != 0) {
                visited[node1] = true;
                node1 = parent[node1];
            }

            // 방문된 곳 찾을때 까지 수행
            while (!visited[node2]) {
                node2 = parent[node2];
            }

            System.out.println(node2);
        }
    }
}
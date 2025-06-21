import java.io.*;
import java.util.*;

public class Main {
    static List<Integer>[] tree;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드 수
        int n = Integer.parseInt(st.nextToken());
        // 물의 양
        double w = Double.parseDouble(st.nextToken());

        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());

            tree[node1].add(node2);
            tree[node2].add(node1);
        }

        int leafCount = 0;
        // 간선이 1개인 노드가 리프노드
        for (int i = 2; i <= n; i++) {
            if (tree[i].size() == 1) leafCount++;
        }

        System.out.printf("%.10f\n", w / leafCount);
    }
}
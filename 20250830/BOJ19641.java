import java.io.*;
import java.util.*;
import java.util.stream.*;

// time : 2960ms
public class BOJ19641 {

    static int current = 1;
    static List<Integer>[] graph;
    static List<Node> nodes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        graph = new List[N + 1];
        for (int i = 1; i < N + 1; i++) graph[i] = new ArrayList<>();

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
        search(S, -1);

        String result = nodes.stream()
                .sorted()
                .map(Node::toString)
                .collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    static void search(int node, int parent) {
        Node n = new Node(node, current++);
        nodes.add(n);

        for (int child : graph[node]) {
            if (child == parent) continue;
            search(child, node);
        }

        n.right = current++;
    }

    static class Node implements Comparable<Node> {
        int num;
        int left;
        int right;

        public Node(int num, int left) {
            this.num = num;
            this.left = left;
        }

        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }

        @Override
        public String toString() {
            return String.format("%d %d %d", num, left, right);
        }
    }
}

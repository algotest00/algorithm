import java.io.*;
import java.util.*;

class Main {
    static int v, e;
    static List<List<Node>> graph; // 인접 리스트

    public static void main(String[] args) throws Exception {
        input();

        System.out.println(solution());
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= v; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 양방향 세팅
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }
    }

    static int solution() {
        // 방문 노드 Set
        Set<Integer> visited = new HashSet<>();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        int result = 0;
        int connected = 0;

        while (!pq.isEmpty() && connected < v) {
            // 노드 선택/가중치 가장 낮은 노드 선택
            Node now = pq.poll();

            if (visited.contains(now.end)) continue;

            visited.add(now.end);
            result += now.cost;
            connected++;

            // 경로 가중치 순 정렬
            for (Node next : graph.get(now.end)) {
                if (!visited.contains(next.end)) {
                    pq.add(next);
                }
            }
        }

        return result;
    }

    static class Node implements Comparable<Node> {
        int end;
        int cost;

        public Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}
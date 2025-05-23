import java.io.*;
import java.util.*;

// 풀이실패 - 크루스칼 알고리즘을 몰랐음.
//  풀이 방식: 크루스칼 알고리즘
//  1. 간선을 가중치 기준으로 정렬한다.
//  2. 유니온 파인드(Disjoint Set)를 활용하여 사이클 여부를 판단한다.
//  3. 서로 다른 집합일 경우 간선을 선택하고 두 집합을 합친다.
//  4. 간선이 (정점 수 - 1)개가 되면 종료한다
public class Main {

    static int[] parent;

    // 부모 노드를 찾는 함수 (경로 압축)
    static int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    // 두 집합을 합치는 함수
    static void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            parent[rootY] = rootX;
        }
    }

    // 간선 클래스 (가중치 기준 정렬용)
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken()); // 정점 개수
        int E = Integer.parseInt(st.nextToken()); // 간선 개수

        // 유니온 파인드를 위한 부모 배열 초기화
        parent = new int[V + 1];
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }

        // 간선 정보 입력 받기
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, weight));
        }

        // 가중치 기준으로 오름차순 정렬
        Collections.sort(edges);

        int totalWeight = 0;
        int count = 0;

        // 크루스칼 알고리즘 실행
        for (Edge edge : edges) {
            if (find(edge.from) != find(edge.to)) {
                union(edge.from, edge.to);
                totalWeight += edge.weight;
                count++;

                // MST는 V-1개의 간선만 필요함
                if (count == V - 1) break;
            }
        }

        // 최소 스패닝 트리의 총 가중치 출력
        System.out.println(totalWeight);
    }
}

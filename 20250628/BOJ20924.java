import java.io.*;
import java.util.*;

public class BOJ20924 {
    static List<Line>[] tree;
    static boolean[]    visited;
    static int          lengthFromRootToGiga = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        // 트리 초기화
        tree = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) tree[i] = new ArrayList<>();

        // 간선 입력
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            tree[a].add(new Line(b, d));
            tree[b].add(new Line(a, d));
        }

        // 기가 노드 찾기
        visited = new boolean[n + 1];
        int giga = findGiga(r);

        // 가지 탐색
        visited = new boolean[n + 1];
        visited[giga] = true;


        // 기가 노드 == 리프 : 가지 X
        if (tree[giga].size() == 1) {
            System.out.println(lengthFromRootToGiga + " " + 0);
            return;
        }

        int maxBranchLength = findMaxBranchLength(giga);
        System.out.println(lengthFromRootToGiga + " " + maxBranchLength);
    }

    // 기가 노드 찾기
    static int findGiga(int r) {
        int node = r;
        visited[node] = true;

        while (true) {
            int count = 0;
            int nextNode = -1;
            int dist = 0;

            for (Line line : tree[node]) {
                if (!visited[line.b]) {
                    count++;
                    nextNode = line.b;
                    dist = line.d;
                }
            }

            // - 기가 노드 ? 루트 노드에서 순회를 시작했을 때, 처음으로 자식 노드가 2개 이상인 노드
            //  - 리프 노드가 단 1개이면 리프노드 = 기가 노드
            if (count >= 2 || count == 0) break;

            visited[nextNode] = true;
            lengthFromRootToGiga += dist;
            node = nextNode;
        }

        return node;
    }

    // 가장 긴 가지 탐색
    static int findMaxBranchLength(int node) {
        boolean isLeaf = true;
        int max = 0;

        for (Line line : tree[node]) {
            if (!visited[line.b]) {
                visited[line.b] = true;
                isLeaf = false;
                int branchLength = findMaxBranchLength(line.b) + line.d;
                max = Math.max(max, branchLength);
            }
        }

        return isLeaf ? 0 : max;
    }

    static class Line {
        int b; // 목적지
        int d; // 간선 길이

        Line(int b, int d) {
            this.b = b;
            this.d = d;
        }
    }
}
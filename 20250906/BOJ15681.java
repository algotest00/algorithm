import java.io.*;
import java.util.*;

public class BOJ15681 {

    static List<Integer>[] tree;

    static int[] queries;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken()); // root
        int Q = Integer.parseInt(st.nextToken());

        tree = new List[N + 1];
        for (int i = 1; i < N + 1; i++) tree[i] = new ArrayList<>();
        queries = new int[N + 1];

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int U = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            tree[U].add(V);
            tree[V].add(U);
        }

        search(R, 0);

        for (int i = 0; i < Q; i++) {
            int query = Integer.parseInt(br.readLine());
            if (query == R) {
                System.out.println(N);
                continue;
            }

            System.out.println(queries[query]);
        }
    }

    static int search(int current, int parent) {
        // 자신 포함 하위노드 개수
        int nodeCounts = 1;

        List<Integer> paths = tree[current];
        // 하위노드가 없으면 돌아가지 않는다.
        for (Integer child : paths) {
            if (child == parent) continue;
            nodeCounts += search(child, current);
        }

        // 쿼리 결과 미리 저장
        queries[current] = nodeCounts;
        return nodeCounts;
    }
}

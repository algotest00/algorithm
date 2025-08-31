import java.io.*;
import java.util.*;

public class BOJ15900 {

    static int leafSum = 0;
    static List<Integer>[] tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        tree = new List[N + 1];
        for (int i = 1; i < N + 1; i++) tree[i] = new ArrayList<>();

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            tree[a].add(b);
            tree[b].add(a);
        }

        findLeaf(1, 0, -1);

        // 깊이 합이 짝수이면 못 이김
        System.out.println(leafSum % 2 == 0 ? "NO" : "YES");
    }

    static void findLeaf(int t, int depth, int parent) {
        // 갈 수 있는 노드가 1개이고, 부모이면 종료
        if (tree[t].size() == 1 && tree[t].get(0) == parent) {
            leafSum += depth;
            return;
        }

        for (int c : tree[t]) {
            // 부모이면 pass
            if (c == parent) continue;
            // 자식은 탐색
            findLeaf(c, depth + 1, t);
        }
    }
}

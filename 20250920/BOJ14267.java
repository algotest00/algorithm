import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ14267 {
    static List<Integer>[] tree;
    static int[]           complimentsInput;
    static int[]           compliments;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 직원수
        int n = Integer.parseInt(st.nextToken());
        // 칭찬 횟수
        int m = Integer.parseInt(st.nextToken());

        complimentsInput = new int[n + 1];
        compliments = new int[n + 1];
        tree = new List[n + 1];
        for (int i = 0; i <= n; i++) tree[i] = new LinkedList<>();

        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent == -1) continue;

            tree[parent].add(i);
        }

        // 칭찬 입력
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int complement = Integer.parseInt(st.nextToken());
            complimentsInput[node] += complement;
        }

        dfs(1, complimentsInput[1]);

        System.out.println(Arrays.stream(compliments).skip(1).mapToObj(Integer::toString).collect(Collectors.joining(" ")));
    }

    // 노드 탐색
    static void dfs(int node, int compliment) {
        for (int c : tree[node]) {
            dfs(c, compliment + complimentsInput[c]);
        }
        compliments[node] = compliment;
    }
}
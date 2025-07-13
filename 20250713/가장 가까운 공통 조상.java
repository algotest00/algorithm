import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        FastReader fr = new FastReader();
        int T = fr.nextInt();

        StringBuilder sb = new StringBuilder();
        while (T-- > 0) {
            int N = fr.nextInt();
            parent = new int[N + 1];
            visited = new boolean[N + 1];

            // 1. 자기 자신이 루트라고 가정
            for (int i = 1; i <= N; i++) parent[i] = i;

            // 2. 자식 -> 부모 저장 (N-1개 간선)
            for (int i = 0; i < N - 1; i++) {
                int p = fr.nextInt();
                int c = fr.nextInt();
                parent[c] = p;//c의 부모는 p
            }
            //3. a와 b의 공통조상 구하기
            int a = fr.nextInt();
            int b = fr.nextInt();

            //4. a의 모든 조상 방문 체크
            while (a != parent[a]) {
                visited[a] = true;
                a = parent[a];
            }
            visited[a] = true; // 루트도 체크

            //5. b의 조상 중 처음으로 방문 체크된 노드 찾기
            while (!visited[b]) {
                b = parent[b];
            }

            sb.append(b).append('\n');
        }

        System.out.print(sb);
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

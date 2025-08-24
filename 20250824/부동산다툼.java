import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 20364 부동산 다툼
// 문제 링크: https://www.acmicpc.net/problem/20364
// 상위 노드에 점유되어 있으면 하위 노드에 점유되어 있는 것으로 표시
public class 부동산다툼 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int n, q;
    static int[] arr;

    public static void main(String[] args) {
        n = sc.nextInt();
        q = sc.nextInt();
        arr = new int[n + 1];

        for (int i = 1; i <= q; i++) {
            int val = sc.nextInt();
            if (arr[val] == 0) {
                sb.append(0).append("\n");

                dfs(val, val);
            } else {
                sb.append(arr[val]).append("\n");
            }
        }

        System.out.print(sb.toString());
    }

    // 점유를했다면 트리의 하위 노드를 점유한 것으로 표시
    static void dfs(int start, int val) {
        arr[start] = val;

        int left = 2 * start;
        int right = 2 * start + 1;

        if (left <= n) {
            arr[left] = val;
            dfs(left, val);
        }

        if (right <= n) {
            arr[right] = val;
            dfs(right, val);
        }
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
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
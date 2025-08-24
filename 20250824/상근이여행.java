import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// BOJ 9372 상근이의 여행
// 문제 링크: https://www.acmicpc.net/problem/9372
// 아이디어: 모든 나라를 한 번씩 방문하기 위한 최소 항공편 수 = (연결 그래프의) 스패닝 트리 간선 수 = N - 1
//           간선 정보는 결과에 영향 없음(연결 보장). 테스트케이스마다 N-1만 출력하면 됨.
public class 상근이여행 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) {
        int T = sc.nextInt();
        while (T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();

            // 간선 입력은 건너뛰되, 버퍼는 소진
            for (int i = 0; i < M; i++) {
                sc.nextInt(); // u
                sc.nextInt(); // v
            }

            sb.append(N - 1).append('\n');
        }
        System.out.print(sb.toString());
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
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

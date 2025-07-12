import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int T, N;
    static int[] parent;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());    // 테스트 케이스 개수
        while (T-- > 0) {
            N = Integer.parseInt(br.readLine()); // 노드 수
            parent = new int[N + 1];
            visit = new boolean[N + 1];

            for (int j = 1; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());   // 부모
                int y = Integer.parseInt(st.nextToken());   // 자식
                parent[y] = x;  // y의 부모는 x
            }

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            solution(x, y);
        }
    }

    static void solution(int x, int y) {
        // x 노드를 루트까지 이동
        while (x > 0) {
            visit[x] = true;    // 방문한 노드는 true로 표시
            x = parent[x];
        }

        while (y > 0) {
            if (visit[y]) {     // x가 방문했던 곳을 y가 방문한 경우 그곳이 가장 가까운 공통 조상
                System.out.println(y);
                break;
            }
            y = parent[y];
        }
    }
}
import java.io.*;
import java.util.*;

/**
 * BOJ 15681 - 트리와 쿼리
 *
 * 문제 요점:
 * - 정점 N, 루트 R, 쿼리 Q
 * - 루트를 R로 하는 "서브트리 크기(정점 수)"를 묻는 쿼리들이 주어진다.
 *
 * - 트리는 간선이 N-1개인 연결 그래프 → 사이클 없음.
 * - 루트 R에서 시작해 부모/자식 관계를 한 번 정하면,
 * 각 정점의 서브트리 크기를 post-order로 한 번에 계산 가능.
 * 
 * - 이렇게 미리 dp[u] = u 서브트리 크기를 구해두면,
 * 쿼리는 dp[u]를 그대로 O(1)에 출력하면 된다.
 * - order[]: 루트부터 방문한 순서(전위 비슷). 이를 "역순"으로 돌며 dp 누적(후위 효과).
 * - 시간복잡도: O(N + Q), 공간복잡도: O(N)
 *
 */
public class 트리와쿼리 {
  static FastReader scan = new FastReader();

  public static void main(String[] args) {
    int N = scan.nextInt(); // 정점 수
    int R = scan.nextInt(); // 루트
    int Q = scan.nextInt(); // 쿼리 수

    ArrayList<Integer>[] adj = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      adj[i] = new ArrayList<>();
    }
    for (int i = 0; i < N - 1; i++) {
      int a = scan.nextInt(), b = scan.nextInt();
      adj[a].add(b);
      adj[b].add(a);
    }

    // 부모/방문순서 만들기
    int[] parent = new int[N + 1]; // parent[root] = -1
    int[] order = new int[N]; // 방문 순서(전위 비슷), 역순으로 dp 누적
    Arrays.fill(parent, 0);

    int idx = 0;
    ArrayDeque<Integer> st = new ArrayDeque<>();
    parent[R] = -1;
    st.push(R);

    while (!st.isEmpty()) {
      int cur = st.pop();
      order[idx++] = cur;

      for (int nxt : adj[cur]) {
        if (nxt == parent[cur]) {
          continue;
        }
        parent[nxt] = cur;
        st.push(nxt);
      }
    }

    // 서브트리 크기 dp 후위 누적
    int[] dp = new int[N + 1];
    Arrays.fill(dp, 1);

    for (int i = N - 1; i >= 0; i--) { // order 역순
      int v = order[i];
      int p = parent[v];
      if (p != -1) {
        dp[p] += dp[v];
      }
    }

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < Q; i++) {
      int u = scan.nextInt();
      sb.append(dp[u]).append('\n');
    }

    System.out.print(sb);
  }

  static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String next() {
      while (st == null || !st.hasMoreTokens()) {
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
  }
}

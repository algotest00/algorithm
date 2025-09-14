/*
 * BOJ 4803 - 트리
 * https://www.acmicpc.net/problem/4803
 *
 * 풀이 방식:
 * - 그래프를 인접 리스트로 구성
 * - DFS로 각 연결 요소의 정점 수, 간선 수를 센다
 * - 트리 조건: 간선 수 = (정점 수 - 1) * 2
 * - 연결 요소별로 검사해 트리 개수 계산
 */

import java.io.*;
import java.util.*;

public class 트리 {
  static FastReader scan = new FastReader();
  static StringBuilder sb = new StringBuilder();

  static int n, m, vertex_cnt, edge_cnt;
  static ArrayList<Integer>[] adj;
  static boolean[] visit;

  static void dfs(int x) {
    vertex_cnt++;
    edge_cnt += adj[x].size();
    visit[x] = true;
    for (int y : adj[x]) {
      if (visit[y])
        continue;
      dfs(y);
    }
  }

  public static void main(String[] args) {
    for (int tt = 1;; tt++) {
      n = scan.nextInt();
      m = scan.nextInt();
      if (n == 0 && m == 0)
        break;

      adj = new ArrayList[n + 1];
      visit = new boolean[n + 1];
      for (int i = 1; i <= n; i++)
        adj[i] = new ArrayList<>();

      for (int i = 1; i <= m; i++) {
        int x = scan.nextInt(), y = scan.nextInt();
        adj[x].add(y);
        adj[y].add(x);
      }

      int ans = 0;
      for (int i = 1; i <= n; i++) {
        if (visit[i])
          continue;
        vertex_cnt = 0;
        edge_cnt = 0;
        dfs(i);
        if (edge_cnt == (vertex_cnt - 1) * 2)
          ans++;
      }

      sb.append("Case ").append(tt).append(": ");
      if (ans == 0) {
        sb.append("No trees.\n");
      } else if (ans == 1) {
        sb.append("There is one tree.\n");
      } else {
        sb.append("A forest of ").append(ans).append(" trees.\n");
      }
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
          e.printStackTrace();
        }
      }
      return st.nextToken();
    }

    int nextInt() {
      return Integer.parseInt(next());
    }
  }
}

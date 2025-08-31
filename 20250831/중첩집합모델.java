import java.io.*;
import java.util.*;

/*
 * BOJ 19641 중첩 집합 모델
 * 문제 링크: https://www.acmicpc.net/problem/19641
 *
 * 풀이 핵심 요약
 * 1) 루트 S에서 DFS (자식은 번호가 작은 순서로 방문).
 * 2) 노드에 진입할 때 left[node] = ++time,
 *    모든 자식 방문 후 빠져나올 때 right[node] = ++time.
 * 3) 마지막에 1..N 순서대로 (node, left, right) 출력.
 */
public class 중첩집합모델 {

  static FastReader sc = new FastReader();
  static int N, S;
  static List<Integer>[] graph;
  static int[] left, right;
  static int time = 0;

  public static void main(String[] args) {
    N = sc.nextInt();
    graph = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++)
      graph[i] = new ArrayList<>();

    for (int i = 0; i < N; i++) {
      int a = sc.nextInt();
      while (true) {
        int b = sc.nextInt();
        if (b == -1)
          break;
        graph[a].add(b);
      }
    }

    S = sc.nextInt(); // 루트 노드

    for (int i = 1; i <= N; i++) {
      if (!graph[i].isEmpty()) {
        Collections.sort(graph[i]);
      }
    }

    left = new int[N + 1];
    right = new int[N + 1];

    dfs(S);

    StringBuilder sb = new StringBuilder();
    for (int node = 1; node <= N; node++) {
      sb.append(node).append(' ').append(left[node]).append(' ').append(right[node]).append('\n');
    }
    System.out.print(sb.toString());
  }

  static void dfs(int node) {
    left[node] = ++time;
    for (int nxt : graph[node]) {
      if (left[nxt] == 0) {
        dfs(nxt);
      }
    }
    right[node] = ++time;
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

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

    long nextLong() {
      return Long.parseLong(next());
    }

    String nextLine() {
      try {
        return br.readLine();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
  }
}

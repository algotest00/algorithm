import java.io.*;
import java.util.*;

/**
 * BOJ 1240 - 노드사이의 거리
 * - N개의 노드로 이루어진 트리(사이클 없음, 간선은 N-1개)
 * - 간선마다 거리가 주어짐
 * - M개의 쿼리: 두 노드 사이의 거리 출력
 *
 * - 트리에서 임의 두 노드 사이 경로는 항상 "하나뿐"
 * - 따라서 매 쿼리마다 BFS/DFS를 돌려서
 * 시작 노드 -> 목표 노드까지 누적 거리 계산
 */
public class 노드사이의거리 {
  static List<Edge>[] g;
  static int N, M;

  public static void main(String[] args) throws Exception {
    FastReader scan = new FastReader();
    StringBuilder out = new StringBuilder();

    N = scan.nextInt();
    M = scan.nextInt();

    g = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++)
      g[i] = new ArrayList<>();

    for (int i = 0; i < N - 1; i++) {
      int a = scan.nextInt();
      int b = scan.nextInt();
      int d = scan.nextInt();
      g[a].add(new Edge(b, d));
      g[b].add(new Edge(a, d));
    }

    for (int i = 0; i < M; i++) {
      int u = scan.nextInt();
      int v = scan.nextInt();
      out.append(distBfs(u, v)).append('\n');
    }
    System.out.print(out.toString());
  }

  /** u -> v 까지의 트리 경로 거리 합 (BFS) */
  static int distBfs(int start, int target) {
    boolean[] vis = new boolean[N + 1];
    ArrayDeque<int[]> q = new ArrayDeque<>();
    q.add(new int[] { start, 0 });
    vis[start] = true;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int x = cur[0], acc = cur[1];
      if (x == target)
        return acc;

      for (Edge e : g[x]) {
        if (!vis[e.to]) {
          vis[e.to] = true;
          q.add(new int[] { e.to, acc + e.w });
        }
      }
    }
    return -1;
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

  }

  static class Edge {
    final int to, w;

    Edge(int to, int w) {
      this.to = to;
      this.w = w;
    }
  }

}

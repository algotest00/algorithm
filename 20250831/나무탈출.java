import java.io.*;
import java.util.*;

/*
 * BOJ 15900 나무 탈출 (Tree Escape)
 * 문제 링크: https://www.acmicpc.net/problem/15900
 *
 * 풀이 핵심 요약
 * 1) 루트(1)에서 시작해 깊이를 구하고,
 * 2) 루트가 아닌 리프(node != 1 && degree == 1)의 깊이를 모두 더함.
 * 3) 합이 홀수면 Yes(선공 승), 짝수면 No.
 *
 */
public class 나무탈출 {

  static FastReader sc = new FastReader();
  static int N;
  static List<Integer>[] graph;

  public static void main(String[] args) {
    N = sc.nextInt();
    graph = new ArrayList[N + 1];
    for (int i = 1; i <= N; i++) {
      graph[i] = new ArrayList<>();
    }
    for (int i = 0; i < N - 1; i++) {
      int a = sc.nextInt(), b = sc.nextInt();
      graph[a].add(b);
      graph[b].add(a);
    }

    boolean[] visited = new boolean[N + 1];
    Queue<int[]> q = new LinkedList<>();
    q.offer(new int[] { 1, 0 }); // {node, depth}
    visited[1] = true;

    long leafDepthSum = 0L;

    while (!q.isEmpty()) {
      int[] cur = q.poll();
      int node = cur[0], depth = cur[1];

      if (node != 1 && graph[node].size() == 1) {
        leafDepthSum += depth;
      }

      for (int nxt : graph[node]) {
        if (!visited[nxt]) {
          visited[nxt] = true;
          q.offer(new int[] { nxt, depth + 1 });
        }
      }
    }

    System.out.println((leafDepthSum & 1L) == 1L ? "Yes" : "No");
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

import java.io.*;
import java.util.*;

/**
 * BOJ 20924 트리 - 기가 노드와 가지 길이 계산
 */
public class Main {
  static FastReader reader = new FastReader();
  static int nodeCount, root;
  static ArrayList<Edge>[] adjList;
  static boolean[] visited;
  static int giga = -1;
  static long stemLen = 0;
  static long maxLeafPath = 0;

  public static void main(String[] args) {
    nodeCount = reader.nextInt();
    root = reader.nextInt();

    adjList = new ArrayList[nodeCount + 1];
    visited = new boolean[nodeCount + 1];

    for (int i = 1; i <= nodeCount; i++)
      adjList[i] = new ArrayList<>();

    for (int i = 0; i < nodeCount - 1; i++) {
      int u = reader.nextInt();
      int v = reader.nextInt();
      int cost = reader.nextInt();
      adjList[u].add(new Edge(v, cost));
      adjList[v].add(new Edge(u, cost));
    }

    findStem();
    visited[giga] = true;
    findLongestLeaf(giga, 0);

    System.out.println(stemLen + " " + maxLeafPath);
  }

  /**
   * 루트부터 기가 노드까지 기둥 길이 계산
   * 조건: 루트는 2개 이상 연결, 중간은 3개 이상 연결 시 기가 노드
   */
  static void findStem() {
    int current = root;
    visited[current] = true;

    while (true) {
      if ((current == root && adjList[current].size() >= 2)
          || (current != root && adjList[current].size() >= 3)) {
        giga = current;
        break;
      }

      boolean moved = false;
      for (Edge next : adjList[current]) {
        if (!visited[next.to]) {
          stemLen += next.weight;
          visited[next.to] = true;
          current = next.to;
          moved = true;
          break;
        }
      }

      if (!moved) {
        giga = current; // 끝까지 내려갔으면 그게 곧 기가 노드
        break;
      }
    }
  }

  /**
   * 기가 노드에서 가장 긴 가지(리프까지) 계산
   */
  static void findLongestLeaf(int current, long sum) {
    boolean hasChild = false;

    for (Edge next : adjList[current]) {
      if (!visited[next.to]) {
        visited[next.to] = true;
        hasChild = true;
        findLongestLeaf(next.to, sum + next.weight);
      }
    }

    if (!hasChild) {
      maxLeafPath = Math.max(maxLeafPath, sum);
    }
  }

  static class Edge {
    int to, weight;

    Edge(int to, int weight) {
      this.to = to;
      this.weight = weight;
    }
  }

  static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String next() {
      while (st == null || !st.hasMoreTokens()) {
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

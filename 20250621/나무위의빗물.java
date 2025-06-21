import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 트리 구조에서 물 w는 리프 노드에만 고이므로, 리프 노드 수로 균등하게 나눔
// 루트(1번) 제외하고 간선이 1개인 노드만 리프 노드로 간주
// 모든 간선은 균등 분배되므로 물은 최종적으로 리프 노드에만 남음
// 결과는 (w / 리프 노드 수)
public class 나무위의빗물 {
  static FastReader scan = new FastReader();
  static ArrayList<Integer>[] edgeLists;

  public static void main(String[] args) {
    int n = scan.nextInt();
    int w = scan.nextInt();
    edgeLists = new ArrayList[n + 1];

    for (int i = 1; i <= n; i++) {
      edgeLists[i] = new ArrayList<>();
    }

    // 간선 입력 (무방향 트리)
    for (int i = 1; i <= n - 1; i++) {
      int node1 = scan.nextInt();
      int node2 = scan.nextInt();
      edgeLists[node1].add(node2);
      edgeLists[node2].add(node1);
    }

    int leafNodeCnt = 0;
    for (int i = 2; i <= n; i++) {
      // 1번노드를 제외한 간선이 1개인 노드는 리프노드이다.
      if (edgeLists[i].size() == 1) {
        leafNodeCnt++;
      }
    }
    System.out.println((double) w / leafNodeCnt);

  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (Exception e) {
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

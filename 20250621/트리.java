import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * - 트리 구조에서 특정 노드를 제거한 뒤, 남은 리프 노드의 개수를 계산하는 문제
 * - 부모-자식 관계로 트리를 구성하고, BFS를 통해 리프 노드를 탐색
 * - 제거된 노드는 방문 처리하여 하위 노드가 무시되도록 하고,
 * 살아있는 자식이 없는 노드를 리프 노드로 간주하여 카운팅
 */
public class 트리 {
  static FastReader scan;
  static ArrayList<Integer>[] childOf;
  static boolean[] isVisited;

  // 부모의 자식들을 기록하는 배열생성.
  // 이후 제거해야할 노드를 선택하면, 그 노드의 부모의 자식들을 돌면서 리프노트갯수 세기.
  public static void main(String[] args) {
    scan = new FastReader();
    int n = scan.nextInt();
    childOf = new ArrayList[n];
    isVisited = new boolean[n];

    // 초기화
    for (int i = 0; i < n; i++) {
      childOf[i] = new ArrayList<>();
    }
    // root 및 기록
    int root = -1;
    for (int i = 0; i < n; i++) {
      int parent = scan.nextInt();
      if (parent == -1) {
        root = i;
      } else {
        childOf[parent].add(i);
      }
    }
    // 제거할 노드 입력
    int removeNode = scan.nextInt();
    // 제거 대상이 루트일 경우, 전체 삭제되므로 0 출력 후 종료
    if (root == removeNode) {
      System.out.println(0);
      return;
    }

    // BFS를 위한 큐 초기화
    Queue<Integer> queue = new LinkedList<>();
    queue.add(root);
    int answer = 0;
    isVisited[removeNode] = true;

    // BFS로 트리를 순회하며 리프 노드 개수 카운팅
    while (!queue.isEmpty()) {
      int node = queue.poll();
      ArrayList<Integer> children = childOf[node];

      boolean hasLiveChild = false;
      for (int child : children) {
        if (!isVisited[child]) {
          queue.add(child);
          hasLiveChild = true;
        }
      }

      if (!hasLiveChild) {
        answer++; // 살아있는 자식이 없으면 리프 노드로 간주
      }
    }
    System.out.println(answer);

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

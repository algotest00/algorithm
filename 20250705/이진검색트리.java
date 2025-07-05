import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * 이진 탐색 트리 후위 순회
 * - 전위 순회로 입력된 노드들을 저장한다.
 * - traverse 함수로 왼쪽, 오른쪽 서브트리를 재귀로 방문한다.
 * - 루트 노드를 마지막에 출력하여 후위 순회 결과를 만든다.
 */
public class Main {
  static StringBuilder sb = new StringBuilder();
  static ArrayList<Integer> list = new ArrayList<>();

  static void input() throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String a = "";
    while ((a = br.readLine()) != null) {
      list.add(Integer.parseInt(a));
    }
  }

  static void traverse(int L, int R) {
    if (L > R)
      return;
    int mid = R; // 왼쪽과 오른쪽 subtree를 가르는 기준 위치를 나타냄
    for (int i = L + 1; i <= R; i++) {
      if (list.get(i) > list.get(L)) {
        mid = i - 1;
        break;
      }
    }
    traverse(L + 1, mid);
    traverse(mid + 1, R);
    sb.append(list.get(L)).append("\n");
  }

  static void pro() {
    traverse(0, list.size() - 1);
    System.out.println(sb.toString());
  }

  public static void main(String[] args) throws IOException {
    input();
    pro();
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

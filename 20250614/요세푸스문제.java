import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 요세푸스문제 {
  static FastReader scan = new FastReader();

  public static void main(String[] args) {
    int n = scan.nextInt(); // 사람 수
    int k = scan.nextInt(); // 제거할 순번

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i <= n; i++) {
      queue.add(i);
    }

    StringBuilder sb = new StringBuilder();
    sb.append("<");

    while (!queue.isEmpty()) {
      for (int i = 0; i < k - 1; i++) {
        queue.add(queue.poll()); // 앞에서 꺼내서 뒤로 넣음 (k번째 전까지)
      }
      sb.append(queue.poll()); // k번째 제거
      if (!queue.isEmpty()) {
        sb.append(", ");
      }
    }

    sb.append(">");
    System.out.println(sb);
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

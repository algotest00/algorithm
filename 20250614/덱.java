import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class 덱 {
  static FastReader scan = new FastReader();

  public static void main(String[] args) {
    int n = scan.nextInt();
    Deque<Integer> deque = new ArrayDeque<>();
    StringBuilder sb = new StringBuilder();

    for (int i = 0; i < n; i++) {
      String cmd = scan.next();

      switch (cmd) {
        case "push_front":
          deque.addFirst(scan.nextInt());
          break;
        case "push_back":
          deque.addLast(scan.nextInt());
          break;
        case "pop_front":
          sb.append(deque.isEmpty() ? -1 : deque.pollFirst()).append("\n");
          break;
        case "pop_back":
          sb.append(deque.isEmpty() ? -1 : deque.pollLast()).append("\n");
          break;
        case "size":
          sb.append(deque.size()).append("\n");
          break;
        case "empty":
          sb.append(deque.isEmpty() ? 1 : 0).append("\n");
          break;
        case "front":
          sb.append(deque.isEmpty() ? -1 : deque.peekFirst()).append("\n");
          break;
        case "back":
          sb.append(deque.isEmpty() ? -1 : deque.peekLast()).append("\n");
          break;
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

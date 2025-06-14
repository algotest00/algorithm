import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class 큐2 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int n = Integer.parseInt(br.readLine());
    Deque<Integer> dq = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      String command = st.nextToken();

      switch (command) {
        case "push":
          dq.offer(Integer.parseInt(st.nextToken()));
          break;

        case "pop":
          sb.append(dq.isEmpty() ? -1 : dq.poll()).append("\n");
          break;

        case "size":
          sb.append(dq.size()).append("\n");
          break;

        case "empty":
          sb.append(dq.isEmpty() ? 1 : 0).append("\n");
          break;

        case "front":
          sb.append(dq.isEmpty() ? -1 : dq.peek()).append("\n");
          break;

        case "back":
          sb.append(dq.isEmpty() ? -1 : dq.peekLast()).append("\n");
          break;
      }
    }

    System.out.print(sb);
  }
}

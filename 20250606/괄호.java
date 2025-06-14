import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;
import java.util.StringTokenizer;

public class 괄호 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    int N = Integer.parseInt(br.readLine());

    for (int i = 0; i < N; i++) {
      String line = br.readLine();
      Stack<Character> stack = new Stack<>();
      boolean isValid = true;

      for (int j = 0; j < line.length(); j++) {
        char ch = line.charAt(j);
        if (ch == '(') {
          stack.push(ch);
        } else {
          if (stack.isEmpty()) {
            sb.append("NO\n");
            isValid = false;
            break;
          } else {
            stack.pop();
          }
        }
      }

      if (!isValid) continue;

      if (stack.isEmpty()) {
        sb.append("YES\n");
      } else {
        sb.append("NO\n");
      }
    }

    System.out.print(sb);
  }
}

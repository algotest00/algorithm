import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for (int i = 0; i < t; i++) {
            String input = br.readLine();
            Stack<Character> stack = new Stack<>();

            boolean isValid = true;
            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);

                if (c == '(') {
                    stack.push(c);
                    continue;
                }

                if (stack.isEmpty()) {
                    isValid = false;
                    break;
                }
                stack.pop();
            }

            if (!stack.isEmpty()) isValid = false;

            System.out.println(isValid ? "YES" : "NO");
        }
    }
}
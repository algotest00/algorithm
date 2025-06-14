import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) queue.add(i);

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        while (!queue.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                // k-1은 큐 마지막에 삽입
                queue.add(queue.poll());
            }

            // k번째 제거
            sb.append(queue.poll());

            if (!queue.isEmpty()) sb.append(", ");
        }

        sb.append(">");
        System.out.println(sb);
    }
}
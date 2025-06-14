import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 1; i <= n; i++) queue.add(i);

        while (queue.size() > 1) {
            // 1. 맨 위 카드 버림
            queue.poll();
            // 2. 다음 카드 마지막으로 삽입
            queue.add(queue.poll());
        }

        System.out.println(queue.poll());
    }
}
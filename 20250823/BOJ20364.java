import java.io.*;
import java.util.*;

public class BOJ20364 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i < Q; i++) {
            // i + 1 번 오리가 가지고 싶은 땅 번호
            int x = Integer.parseInt(br.readLine());

            int current = x;

            // 배치 가능하면 0, 불가능하면 처음 마주치는 땅 번호 출력
            int first = 0;
            while (current > 1) {
                if (visited[current]) first = current;
                current /= 2;
            }

            if (first == 0) visited[x] = true;
            System.out.println(first);
        }
    }
}

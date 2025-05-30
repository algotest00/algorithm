import java.io.*;
import java.util.*;

//노답 어려버서 완전 보고 풀엇서요 ㅠ
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        boolean[] vtd = new boolean[V+1];
        int count = V;
        int result = 0;
        ArrayList<int[]>[] nodes = new ArrayList[V+1];
        for (int v = 0; v <= V; v++) {
            nodes[v] = new ArrayList<>();
        }
        for (int e = 0; e < E; e++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            nodes[A].add(new int[]{B,C});
            nodes[B].add(new int[]{A,C});
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        queue.add(new int[]{1, 0});
        while (count > 0 && !queue.isEmpty()) {
            int[] now = queue.poll();
            if (vtd[now[0]]) continue;
            vtd[now[0]] = true;
            count--;
            result += now[1];
            for (int[] node : nodes[now[0]]) {
                if (!vtd[node[0]]) queue.add(node);
            }
        }
        System.out.println(result);
    }
}
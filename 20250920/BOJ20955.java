import java.io.*;
import java.util.*;

public class BOJ20955 {
    static int[] roots;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        roots = new int[N + 1];
        for (int i = 1; i <= N; i++) roots[i] = i;

        // 서브트리 개수
        int treeCount = N;
        // 사이클 수
        int cycleCount = 0;

        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            if (isCycle(u, v)) cycleCount++;
            else treeCount--;
        }

        System.out.println(cycleCount + treeCount - 1);
    }

    // 노드의 루트 찾기
    static int findRoot(int node){
        if (roots[node] == node) return node;
        roots[node] = findRoot(roots[node]);
        return roots[node];
    }

    // 사이클 체크
    static boolean isCycle(int u, int v) {
        int uRoot = findRoot(u);
        int vRoot = findRoot(v);
        
        // u, v의 루트가 같으면 사이클
        if (uRoot == vRoot) return true;
        
        // v의 루트에 u의 루트를 할당
        roots[vRoot] = uRoot;
        return false;
    }
}
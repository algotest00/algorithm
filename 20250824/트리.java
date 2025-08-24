import java.io.*;
import java.util.*;

public class 트리 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();

    static int n;
    static int[] preOrder, inOrder, pos; // pos[val] = inorder index

    public static void main(String[] args) {
        int T = sc.nextInt();
        while (T-- > 0) {
            n = sc.nextInt();

            preOrder = new int[n];
            inOrder = new int[n];

            for (int i = 0; i < n; i++)
                preOrder[i] = sc.nextInt();
            for (int i = 0; i < n; i++)
                inOrder[i] = sc.nextInt();

            pos = new int[Math.max(n + 1, 100001)];
            for (int i = 0; i < n; i++)
                pos[inOrder[i]] = i;

            buildPostorder(0, n - 1, 0, n - 1);
            sb.append('\n'); // 테스트케이스 구분
        }

        System.out.print(sb.toString());
    }

    // 후위: Left -> Right -> Root
    private static void buildPostorder(int inL, int inR, int preL, int preR) {
        if (inL > inR || preL > preR)
            return;

        int root = preOrder[preL]; // preOrder 시작 = 루트
        int k = pos[root]; // inOrder에서 루트 위치
        int leftSize = k - inL; // 왼쪽 서브트리 노드 수

        // 왼쪽, 오른쪽 먼저 출력(후위 규칙)
        buildPostorder(inL, k - 1, preL + 1, preL + leftSize);
        buildPostorder(k + 1, inR, preL + leftSize + 1, preR);

        sb.append(root).append(' ');
    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }
    }
}

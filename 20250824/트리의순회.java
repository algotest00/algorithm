import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// BOJ 2263번 트리의 순회
// 문제 링크: https://www.acmicpc.net/problem/2263
// 풀이 참조: https://pepperminttt.tistory.com/91
// Pre-order (전위 순회) → Root → Left → Right
// In-order (중위 순회) → Left → Root → Right
// Post-order (후위 순회) → Left → Right → Root -> 젤끝에 있는 수가 root임

public class 트리의순회 {
    static FastReader sc = new FastReader();
    static StringBuilder sb = new StringBuilder();
    static int n;
    static int[] inOrder, postOrder;
    public static ArrayList<Integer> preOrder = new ArrayList<>();

    public static void main(String[] args) {
        n = sc.nextInt();
        inOrder = new int[n];
        postOrder = new int[n];

        for (int i = 0; i < n; i++) {
            inOrder[i] = sc.nextInt();
        }
        for (int i = 0; i < n; i++) {
            postOrder[i] = sc.nextInt();
        }

        getPreorder(0, n - 1, 0, n - 1);
        for (int i = 0; i < n; i++) {
            sb.append(preOrder.get(i) + " ");
        }
        System.out.println(sb.toString());

    }

    private static void getPreorder(int inStart, int inEnd, int postStart, int postEnd) {
        if (inStart > inEnd || postStart > postEnd)
            return;
        // 포스트오더의 끝자리를 넣어줌.
        int root = postOrder[postEnd];
        int index = 0;
        for (int i = 0; i < inOrder.length; i++) {
            if (root == inOrder[i])
                index = i;
        }
        preOrder.add(root);
        int size = index - inStart;
        getPreorder(inStart, index - 1, postStart, postStart + size - 1);
        getPreorder(index + 1, inEnd, postStart + size, postEnd - 1);

    }

    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
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

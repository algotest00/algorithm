import java.io.*;
import java.util.*;
import java.util.stream.*;

public class BOJ2263 {
    static int[] in, post, pre;
    static int[] inPos; // 중위 순회 요소 인덱싱
    static int   prePointer = 0;
    static int   postPointer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        in = new int[n];
        post = new int[n];
        inPos = new int[n + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
            inPos[in[i]] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) post[i] = Integer.parseInt(st.nextToken());

        pre = new int[n];
        postPointer = n - 1;

        preOrder(0, n - 1, 0, n - 1);

        System.out.println(Arrays.stream(pre).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
    }

    /**
     * 중위, 후위 순회 기반 전위 순회
     *
     * @param inLeft    중위 탐색 범위 왼쪽 인덱스
     * @param inRight   중위 탐색 범위 오른쪽 인덱스
     * @param postLeft  후위 탐색 범위 왼쪽 인덱스
     * @param postRight 후위 탐색 범위 오른쪽 인덱스
     */
    static void preOrder(int inLeft, int inRight, int postLeft, int postRight) {
        if (inLeft > inRight || postLeft > postRight) return;

        // 루트 찾기 : 후위의 마지막은 루트임
        int root = post[postRight];
        pre[prePointer++] = root;

        // 중위에서 루트 위치
        int inOrderedRootPos = inPos[root];
        // 왼쪽 서브트리 크기
        int leftSize = inOrderedRootPos - inLeft;

        // 왼쪽 서브트리 탐색 : 중위 기준 루트 왼쪽까지, 후위 기준 왼쪽 서브트리 길이만큼
        preOrder(inLeft, inOrderedRootPos - 1, postLeft, postLeft + leftSize - 1);
        // 오른쪽 서브트리 탐색 : 중위 기준 루트 오른쪽부터 끝까지, 후위 기준 왼쪽 서브트리 다음부터 루트 전까지
        preOrder(inOrderedRootPos + 1, inRight, postLeft + leftSize, postRight - 1);
    }
}

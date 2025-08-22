import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ4256 {
    static int preRootIdx, postPointer;
    static int[] pre, in, post;
    static int[] inPos; // 중위순회 요소 인덱싱

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            // 노드 개수
            int n = Integer.parseInt(br.readLine());

            pre = new int[n];
            in = new int[n];
            inPos = new int[n + 1];

            // 전위 입력
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) pre[j] = Integer.parseInt(st.nextToken());

            // 중위 입력
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                in[j] = Integer.parseInt(st.nextToken());
                // 중위 순회값 인덱스 체크
                inPos[in[j]] = j;
            }

            preRootIdx = 0; // 루트 인덱스 포인터. 순회마다 다음 서브트리의 루트를 찾기 위함
            post = new int[n];
            postPointer = 0; // 후위순회 값 채울 위치 포인터
            postOrder(0, n - 1);

            System.out.println(Arrays.stream(post).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    // 후위 : left -> right -> mid
    static void postOrder(int leftIdx, int rightIdx) {
        if (leftIdx > rightIdx) return;

        int root = pre[preRootIdx++];
        int inOrderRootIndex = inPos[root];

        // 왼쪽 서브트리 순회
        postOrder(leftIdx, inOrderRootIndex - 1);

        // 오른쪽 서브트리 순회
        postOrder(inOrderRootIndex + 1, rightIdx);

        // 루트 입력
        post[postPointer++] = root;
    }
}
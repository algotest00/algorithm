import java.io.*;
import java.util.*;

public class BOJ9489 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 노드 수
            int n = Integer.parseInt(st.nextToken());
            // 사촌의 수를 구해야 하는 노드의 번호
            int k = Integer.parseInt(st.nextToken());

            // 0 0 입력시 종료
            if (n == 0 && k == 0) break;

            int kIndex = -1;
            // 입력값 저장
            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                int value = Integer.parseInt(st.nextToken());
                arr[i] = value;

                if (value == k) kIndex = i;
            }

            List<Node> nodeList = new ArrayList<>();
            // 루트 저장
            nodeList.add(new Node(arr[0], 0, -1, -1));

            int pointer = 0;
            int i = 1;
            while (i < n) {
                int start = i;
                int end = i;
                while (end + 1 < n && arr[end] + 1 == arr[end + 1]) end++;

                Node parent = nodeList.get(pointer);
                // 현재 포인터에 자식노드로 저장
                for (int j = start; j <= end; j++) {
                    nodeList.add(new Node(arr[j], parent.depth + 1, parent.value, parent.parentValue));
                }

                // 포인터 증가
                pointer++;

                // 다음 탐색
                i = end + 1;
            }

            Node kNode = nodeList.get(kIndex);

            // 사촌의 수 구하기
            int count = 0;
            for (Node node : nodeList) {
                if (node.value == k) continue;

                // 같은 Depth, 부모가 다르고, 조부모가 같으면 사촌임
                if (node.depth == kNode.depth
                        && node.parentValue != kNode.parentValue
                        && node.grandParentValue == kNode.grandParentValue
                ) count++;
            }

            System.out.println(count);
        }
    }

    static class Node {
        int value;
        int depth;
        int parentValue;
        int grandParentValue;

        Node(int value, int depth, int parentValue, int grandParentValue) {
            this.value = value;
            this.depth = depth;
            this.parentValue = parentValue;
            this.grandParentValue = grandParentValue;
        }
    }
}
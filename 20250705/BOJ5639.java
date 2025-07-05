import java.io.*;

public class BOJ5639 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        if (s == null || s.isEmpty()) return;

        Node root = new Node(Integer.parseInt(s));

        // 이진 검색 트리 생성
        while (true) {
            s = br.readLine();
            if (s == null || s.isEmpty()) break;
            root.insert(Integer.parseInt(s));
        }

        postOrder(root);
        System.out.println(sb.toString());
    }

    /**
     * 후위 순회
     * 왼쪽 서브 트리 먼저 순회 및 출력 -> 오른쪽 서브 트리 순회 및 출력 -> 입력 노드 출력
     * @param node 탐색 노드
     */
    static void postOrder(Node node) {
        if (node == null) return;
        postOrder(node.left);
        postOrder(node.right);
        sb.append(node.value).append('\n');
    }

    static class Node {
        int  value;
        Node left, right;

        Node(int v) {
            this.value = v;
        }

        void insert(int v) {
            // 입력값보다 작으면 왼쪽, 크면 오른쪽
            if (v < this.value) {
                if (left == null) left = new Node(v);
                else left.insert(v);
            } else {
                if (right == null) right = new Node(v);
                else right.insert(v);
            }
        }
    }
}
import java.io.*;
import java.util.*;

public class BOJ2250 {
    static Map<Integer, Node> nodeMap = new HashMap<>();
    static int[]              levelMin;
    static int[]              levelMax;
    static boolean[]          visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        levelMin = new int[n + 1];
        levelMax = new int[n + 1];

        Set<Integer> childSet = new HashSet<>();
        // 노드 입력
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()); // 부모
            int b = Integer.parseInt(st.nextToken()); // 왼쪽
            int c = Integer.parseInt(st.nextToken()); // 오른쪽

            Node parentNode = nodeMap.computeIfAbsent(a, Node::new);

            if (b != -1) {
                parentNode.left = nodeMap.computeIfAbsent(b, Node::new);
                childSet.add(b);
            }

            if (c != -1) {
                parentNode.right = nodeMap.computeIfAbsent(c, Node::new);
                childSet.add(c);
            }
        }

        // 자식 Set이 아닌 노드가 루트 노드
        int rootValue = -1;
        for (int key : nodeMap.keySet()) {
            if (!childSet.contains(key)) {
                rootValue = key;
                break;
            }
        }
        Node root = nodeMap.get(rootValue);

        // 루트부터 중위순회
        visited = new boolean[n + 1];
        inorder(root, 1);

        // 너비 계산
        int maxWidth = 0;
        int level = 0;
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) continue;
            int width = levelMax[i] - levelMin[i] + 1;
            if (width > maxWidth) {
                maxWidth = width;
                level = i;
            }
        }

        System.out.println(level + " " + maxWidth);
    }

    // 노드의 위치 변수
    static int position = 1;

    // 중위순회 하면서 깊이별 최소값, 최대값 계산
    // 중위순회기 때문에 가장 왼쪽 서브트리에 있는 노드부터 1 -> 2 -> ...로 채워짐
    static void inorder(Node node, int depth) {
        if (node == null) return;

        inorder(node.left, depth + 1);

        // 방문 처리
        if (!visited[depth]) {
            levelMin[depth] = position;
            levelMax[depth] = position;
            visited[depth] = true;
        } else {
            levelMin[depth] = Math.min(levelMin[depth], position);
            levelMax[depth] = Math.max(levelMax[depth], position);
        }
        position++;

        inorder(node.right, depth + 1);
    }

    static class Node {
        int  value;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }
}
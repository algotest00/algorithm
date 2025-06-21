import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(-1, i);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int parentKey = Integer.parseInt(st.nextToken());
            if (parentKey == -1) continue;
            nodes[i].parentKey = parentKey;
            nodes[parentKey].children.add(nodes[i]);
        }

        // 노드 제거
        int keyToRemove = Integer.parseInt(br.readLine());
        if (nodes[keyToRemove].parentKey == -1) {
            System.out.println(0);
            return;
        }

        nodes[nodes[keyToRemove].parentKey].children.remove(nodes[keyToRemove]);
        remove(nodes, nodes[keyToRemove]);


        int count = 0;
        for (int i = 0; i < n; i++) {
            // 제거한 노드는 pass
            if (nodes[i] == null) continue;
            if (nodes[i].isLeafNode()) count++;
        }

        System.out.println(count);
    }

    // 특정 노드 및 하위노드 제거
    static void remove(Node[] nodes, Node node) {
        for (Node n : node.children) remove(nodes, n);
        nodes[node.key] = null;
    }

    static class Node {
        int key;
        int parentKey;
        List<Node> children = new ArrayList<>();

        Node(int parentKey, int key) {
            this.parentKey = parentKey;
            this.key = key;
        }

        boolean isLeafNode() {
            return children.isEmpty();
        }
    }
}
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        LinkedList<Integer> queue = new LinkedList<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            String[] input = br.readLine().split(" ");
            String command = input[0];
            switch (command) {
                case "push":
                    int value = Integer.parseInt(input[1]);
                    queue.addLast(value);
                    break;
                case "pop":
                    sb.append(queue.isEmpty() ? -1 : queue.removeFirst()).append('\n');
                    break;
                case "size":
                    sb.append(queue.size()).append('\n');
                    break;
                case "empty":
                    sb.append(queue.isEmpty() ? 1 : 0).append('\n');
                    break;
                case "front":
                    sb.append(queue.isEmpty() ? -1 : queue.getFirst()).append('\n');
                    break;
                case "back":
                    sb.append(queue.isEmpty() ? -1 : queue.getLast()).append('\n');
                    break;
            }
        }

        System.out.print(sb);
    }
}
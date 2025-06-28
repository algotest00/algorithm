import java.io.*;
import java.util.StringTokenizer;

public class Main {
  static FastReader scan = new FastReader();

  public static void main(String[] args) {
    StringBuilder sb = new StringBuilder();

    while (true) {
      int n = scan.nextInt();
      int k = scan.nextInt();
      if (n == 0 && k == 0)
        break;

      int[] arr = new int[n + 1];
      int[] parent = new int[n + 1];
      int targetIdx = 0;
      arr[0] = -1;
      parent[0] = -1;
      int pIdx = -1;

      for (int i = 1; i <= n; i++) {
        arr[i] = scan.nextInt();
        if (arr[i] == k)
          targetIdx = i;
        if (arr[i] != arr[i - 1] + 1)
          pIdx++;
        parent[i] = pIdx;
      }

      // Step 1: 루트면 사촌 없음
      if (targetIdx == 1) {
        sb.append("0\n");
        continue;
      }

      // Step 2: 사촌 개수 계산
      int answer = 0;
      for (int i = 1; i <= n; i++) {
        if (i == targetIdx)
          continue;
        if (parent[i] != parent[targetIdx] && parent[parent[i]] == parent[parent[targetIdx]]) {
          answer++;
        }
      }

      sb.append(answer).append("\n");
    }

    // Step 3: 출력
    System.out.print(sb);
  }

  static class FastReader {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;

    String next() {
      while (st == null || !st.hasMoreTokens()) {
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

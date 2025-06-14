import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 카드2 {
  static FastReader scan = new FastReader();
  public static void main(String[] args) {
    int n = scan.nextInt();
    Queue<Integer> queue = new LinkedList<>();
    for (int i = 1; i<= n; i++) {
      queue.add(i);
    }

    while (!queue.isEmpty()) {
      //한장버리기
      int num = queue.poll();
      if (queue.isEmpty()) {
        System.out.println(num);
      }else {
        //다음장밑에넣기
          queue.add(queue.poll());
      }
    }
  }

  static class FastReader {
    BufferedReader br;
    StringTokenizer st;

    public FastReader() {
      br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
      while (st == null || !st.hasMoreTokens()) {
        try {
          st = new StringTokenizer(br.readLine());
        } catch (Exception e) {
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

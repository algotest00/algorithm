import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		Queue <Integer> que = new ArrayDeque<>();
		for (int i = 1; i<N+1; i++) {
			que.add(i);
		}
		
		while(que.size()>1) { 
			que.poll(); // 바닥에 버리기
			que.add(que.poll()); // 맨 뒤에 넣기
		}
		
		
		System.out.println(que.peek());
	}

}
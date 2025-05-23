import java.io.*;
import java.util.StringTokenizer;

//  알고리즘 분류: 플로이드-워셜 (Floyd-Warshall)
//  풀이 방식:
//  인접 행렬을 기반으로 각 정점 i에서 j로 가는 경로의 존재 여부를 계산한다.
//  중간 노드 k를 거쳐 갈 수 있다면 matrix[i][j] = 1로 설정.
//  이중 삼중 for문으로 모든 정점 쌍에 대해 경로 존재 여부를 갱신한다.
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[][] matrix = new int[N][N];

        // 인접 행렬 입력
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 플로이드-워셜 알고리즘
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (matrix[i][k] == 1 && matrix[k][j] == 1) {
                        matrix[i][j] = 1;
                    }
                }
            }
        }

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb.append(matrix[i][j]).append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }
}

import java.io.*;
import java.util.*;
import java.util.stream.*;

class Main {
    public static void main(String[] args) throws Exception {
        int[][] maps = input();

        // a -> c -> b 경로 있으면 a -> b 경로 있는 것
        for (int c = 0; c < maps.length; c++) {
            for (int a = 0; a < maps.length; a++) {
                for (int b = 0; b < maps.length; b++) {
                    if (maps[a][c] == 1 && maps[c][b] == 1) maps[a][b] = 1;
                }
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int[] map : maps) {
            bw.write(Arrays.stream(map).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }

    static int[][] input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        return arr;
    }
}
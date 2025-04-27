import java.util.*;

class Solution {
    public int solution(int[][] board, int[][] skills) {
        int[][] arr = new int[board.length + 1][board[0].length + 1];

        for (int[] skill : skills) {
            int type = skill[0];
            int r1 = skill[1], c1 = skill[2];
            int r2 = skill[3], c2 = skill[4];
            int degree = skill[5];

            int amount = type == 1 ? -(degree) : degree;

            arr[r1][c1] += amount;
            arr[r1][c2 + 1] += -(amount);
            arr[r2 + 1][c1] += -(amount);
            arr[r2 + 1][c2 + 1] += amount;
        }

        // 누적합 계산
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) arr[r][c + 1] += arr[r][c];
        }

        for (int c = 0; c < board[0].length; c++) {
            for (int r = 0; r < board.length; r++) arr[r + 1][c] += arr[r][c];
        }

        // 스킬 사용 내역 반영
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) board[r][c] += arr[r][c];
        }

        return Arrays.stream(board)
                .mapToInt(b -> Arrays.stream(b).map(i -> i > 0 ? 1 : 0).sum())
                .sum();
    }
}
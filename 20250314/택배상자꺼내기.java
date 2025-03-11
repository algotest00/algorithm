class Solution {
    public int solution(int n, int w, int num) {
        int rows = (n + w - 1) / w;
        int[][] map = new int[rows][w];
        int val = 0, xIdx = 0, yIdx = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < w; j++) {
                val++;

                int col = (i % 2 == 0) ? j : (w - j - 1);
                map[i][col] = val;

                if (val == num) {
                    xIdx = i;
                    yIdx = col;
                }
                if (val == n)
                    break;
            }
            if (val == n)
                break;
        }
        return (map[rows - 1][yIdx] == 0) ? rows - xIdx - 1 : rows - xIdx;
    }
}

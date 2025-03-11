class Solution {
    public int solution(int n, int w, int num) {
        // 최대값의 row
        int maxRow = (int) Math.ceil((double) n / (double) w);
        // 대상 위치 row
        int targetRow = (int) Math.ceil((double) num / (double) w);

        // n row도 가득 찼을때 꺼낼 횟수
        int countOnMax = maxRow - targetRow + 1;

        // 대상 인덱스
        int targetIndex = targetRow % 2 == 0
                ? targetRow * w - num
                : (num % w == 0 ? w : num % w) - 1;
        // n의 행, num의 index 에 해당하는 값
        int numAtPosition = getNum(maxRow, w, targetIndex);

        // numAtPosition 값이 n보다 크면 -1 처리
        return countOnMax + (n < numAtPosition ? -1 : 0);
    }

    private int getNum(int row, int w, int index) {
        if (row == 1) return index + 1;

        // 짝수행
        if (row % 2 == 0) {
            return row * w - index;
        }
        // 홀수행
        else {
            return (row - 1) * w + index + 1;
        }
    }
}
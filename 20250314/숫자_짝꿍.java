class Solution {
    public String solution(String X, String Y) {
        int[] arrX = new int[10];
        X.chars().forEach(c -> arrX[c - 48]++);

        int[] arrY = new int[10];
        Y.chars().forEach(c -> arrY[c - 48]++);

        int[] minArr = new int[10];
        for (int i = 0; i < 10; i++) {
            minArr[i] = Math.min(arrX[i], arrY[i]);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            if (minArr[i] > 0) {
                sb.append(String.valueOf(i).repeat(minArr[i]));
            }
        }

        String resultStrNumber = sb.toString();
        if (resultStrNumber.isEmpty()) return "-1";
        if (resultStrNumber.startsWith("0")) return "0";
        return resultStrNumber;
    }
}
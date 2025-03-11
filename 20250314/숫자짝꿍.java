class Solution {
    public String solution(String X, String Y) {
        StringBuilder answer = new StringBuilder();
        int[] numsX = new int[10];
        int[] numsY = new int[10];
        
        for (int i = 0; i < X.length(); i++) {
            int num = Character.getNumericValue(X.charAt(i));
            numsX[num]++;
        }

        for (int i = 0; i < Y.length(); i++) {
            int num = Y.charAt(i);
            numsY[num]++;
        }
        
        for (int i = 9; i >= 0; i--) {
            int num = Math.min(numsX[i], numsY[i]);
            for (int j = 0; j < num; j++) {
                answer.append(i);
            }
        }
        
        if (answer.length() == 0) return "-1";
        if (answer.charAt(0) == '0') return "0";
        
        return answer.toString();
    }
}

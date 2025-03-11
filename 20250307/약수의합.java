class Solution {
    public int solution(int n) {
        int answer = 0;
        int end = (int) Math.sqrt(n);
        for (int i = 1; i <= end; i++) {
            if(n % i > 0) continue;
            answer += i;
            int pair = n/i;
            if(i != pair) answer += pair;
        }
        
        return answer;
    }
}
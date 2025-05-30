import java.util.*;
class Solution {
    //그리디
    public int solution(int[][] targets) {
        int answer = 0;
        int before = 0;
        Arrays.sort(targets, (x, y) -> x[1]-y[1]);
        for(int i=0; i<targets.length; i++) {
        	if(targets[i][0] >= before) {
        		before = targets[i][1];
        		answer++;
        	}
        }
        return answer;
    }
}
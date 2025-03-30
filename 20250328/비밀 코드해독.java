import java.util.*;

/*
* 조합(Combinations) + 시뮬레이션
* 풀이시간: 3시간
* - 정답이 될 수 있는 조합들을 저장할 때 어떤 자료구조를 써야 할지 고민이 길어짐.
* - 결국 중복 제거와 빠른 포함 여부 판단을 위해 HashSet 사용이 적절했음.
* - 조합 탐색은 DFS로 구현했고, 조건 검증은 시뮬레이션으로 처리함.
*/
class Solution {
    static ArrayList<HashSet<Integer>> list = new ArrayList<HashSet<Integer>>();
    // static boolean isVisited[];
    static int[] nums = new int[5];
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        dfs(0, 1, n);
        
        for(HashSet<Integer> set : list) {
            boolean flag = true;

            for (int i = 0; i < q.length; i++) {
                if(!flag) break;
                int ansCnt = ans[i];
                for (int j = 0; j < q[i].length; j++) {
                    if(!flag) break;
                    if(set.contains(q[i][j])){
                        ansCnt--;
                    }
                }
                

                if(ansCnt != 0) {
                    flag = false;
                    break;
                }
                  
            }
            if(flag) {
                answer++;
            }
            
        }
        return answer;
    }
    
    static void dfs(int ansIdx, int idx, int lastNum) {
        if(ansIdx == 5) {
            HashSet<Integer> set = new HashSet<>();
            for(int i = 0; i < 5; i++){
                set.add(nums[i]);
            }
            list.add(set);
            return;
        }
        for (int i = idx; i <= lastNum; i++) {
            nums[ansIdx] = i;
            dfs(ansIdx + 1, i+1, lastNum);
            nums[ansIdx] = 0;
        }
    }
}
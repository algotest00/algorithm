import java.util.*;

class Solution {
    Params params;
    List<Integer> comb = new ArrayList<>();
    int answer = 0;

    public int solution(int n, int[][] q, int[] ans) {
        params = new Params(n, q, ans);

        findCombAndCheck(1);

        return answer;
    }

    private void findCombAndCheck(int currentNum) {
        if (comb.size() == 5) {
            // 조합 확인
            for (int i = 0; i < params.q.length; i++) {
                int count = 0;
                for (int j = 0; j < 5; j++) {
                    if (comb.contains(params.q[i][j])) count++;
                }
                if (!(count == params.ans[i])) return;
            }

            answer++;
            return;
        }

        for (int i = currentNum; i <= params.n; i++) {
            comb.add(i);
            // dfs : 조합만들기
            findCombAndCheck(i + 1);
            comb.remove(comb.size() - 1);
        }
    }

    // DFS에서 활용하려고 만듦
    class Params {
        final int n;
        final int[][] q;
        final int[] ans;

        Params(int n, int[][] q, int[] ans) {
            this.n = n;
            this.q = q;
            this.ans = ans;
        }
    }
}
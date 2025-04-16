/**
 * [알고리즘 설명]
 * - 이 코드는 DFS(깊이 우선 탐색)를 이용해, 주어진 numbers 배열의 각 원소에 대해
 *   + 또는 - 부호를 붙여 모든 경우의 수를 탐색합니다.
 * - 총 2^n 가지 경우가 존재하며, 각 경우마다 합을 계산하여 target과 일치하는 경우의 수를 셉니다.
 * 
 * [핵심 로직]
 * - 각 인덱스에서 + 또는 - 부호를 선택
 * - 모든 조합합을 탐색한 후 합이 target과 같으면 정답 카운트 증가
 * 
 */

 class Solution {
    static int[] arr;
    static int answer;

    public int solution(int[] numbers, int target) {
        arr = new int[numbers.length];
        dfs(0, numbers, target);
        return answer;
    }
    
    public void dfs(int idx, int[] numbers, int target){
        if (idx == numbers.length) {
            int sum = 0;
            for (int i = 0; i < arr.length; i++) {
                sum += arr[i];
            }
            if(sum == target) answer++;
            return;
        }
        arr[idx] = numbers[idx];       // + 부호 선택
        dfs(idx + 1, numbers, target);

        arr[idx] = -numbers[idx];      // - 부호 선택
        dfs(idx + 1, numbers, target);
    }
}

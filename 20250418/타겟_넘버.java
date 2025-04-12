class Solution {
    int[] arr;
    int v;

    public int solution(int[] numbers, int target) {
        arr = numbers;
        v = target;
        return search(0, 0, 0);
    }

    int search(int currIndex, int currValue, int ans) {
        if (currIndex >= arr.length && currValue == v) return ++ans;
        if (currIndex < arr.length) {
            return search(currIndex + 1, currValue + arr[currIndex], ans)
                    + search(currIndex + 1, currValue - arr[currIndex], ans);
        }
        return 0;
    }
}
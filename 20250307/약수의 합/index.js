// 정수 n을 입력받아 n의 약수의 합을 구하기
// 시간복잡도: O(N)
// -> 1부터 순서대로 순회하는 로직
const solution = (n) => {
    let answer = 0;
    for (let i = 1; i <= n; i++) {
        if (n % i === 0) {
            answer += i;
        }
    }

    return answer;
}

// 루트 n까지만 순회하는 로직
// 시간복잡도: O(√n)
const solution2 = (n) => {
    let ans = 0;
    for (let i = 1; i <= Math.sqrt(n); i++) {
        if (n % i === 0) {
            ans += i;
            if (i !== n / i) ans += n / i;
        }
    }
    return ans;
}

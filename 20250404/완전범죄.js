const solution = (info, n, m) => {
    const len = info.length;
    const MAX_VALUE = 1e9; // 1. 매우 큰 수로 초기화 (불가능한 상태)

    // 2. dp[i][a] : i개의 물건을 처리했을 때, A의 누적 흔적이 a일 때 B의 최소 흔적 수
    let dp = Array.from({ length: len + 1 }, () => Array(n).fill(MAX_VALUE));
    dp[0][0] = 0; // 3. 초기 상태

    // 4. 각 물건을 하나씩 처리하면서 dp 배열 갱신
    for (let i = 0; i < len; i++) {
        const [aTrace, bTrace] = info[i]; // 현재 물건 i의 A, B 흔적

        // 5. 다음 상태를 저장할 배열 초기화
        let nextDP = Array.from({ length: len + 1 }, () => Array(n).fill(MAX_VALUE));

        // 6. 가능한 모든 A의 흔적 수에 대해 반복
        for (let a = 0; a < n; a++) {
            if (dp[i][a] === MAX_VALUE) continue; // 도달할 수 없는 상태는 건너뜀

            // A가 현재 물건을 훔치는 경우
            if (a + aTrace < n) { // A의 누적 흔적이 n 미만일 때
                nextDP[i + 1][a + aTrace] = Math.min(
                    nextDP[i + 1][a + aTrace],
                    dp[i][a] // B의 흔적은 그대로 유지
                );
            }

            // B가 현재 물건을 훔치는 경우
            if (dp[i][a] + bTrace < m) { // B의 누적 흔적이 m 미만일 때
                nextDP[i + 1][a] = Math.min(
                    nextDP[i + 1][a],
                    dp[i][a] + bTrace // A의 흔적은 그대로 B는 흔적 증가
                );
            }
        }

        // 7. 다음 단계의 dp 테이블로 교체
        dp = nextDP;
    }

    // 8. 모든 물건을 처리한 후 조건을 만족하는 A의 최소 흔적을 탐색
    let answer = MAX_VALUE;
    for (let a = 0; a < n; a++) {
        if (dp[len][a] < m) { // A - a 미만 B - m 미만
            answer = Math.min(answer, a); // 최소 A 흔적값 갱신
        }
    }

    // 9. 결과반환
    return answer === MAX_VALUE ? -1 : answer;
};

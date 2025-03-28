// 비밀 코드 해독
function solution(n, q, ans) {
    const candidates = [];

    // 모든 5개 조합을 구하는 함수
    function getCombinations(arr, selectNumber) {
        if (selectNumber === 1) return arr.map((value) => [value]);
        const results = [];

        arr.forEach((fixed, index, origin) => {
            const rest = origin.slice(index + 1);
            const combinations = getCombinations(rest, selectNumber - 1);
            const attached = combinations.map((combo) => [fixed, ...combo]);
            results.push(...attached);
        });

        return results;
    }

    // 1부터 n까지의 모든 수로 가능한 조합을 생성
    const allCombinations = getCombinations(Array.from({ length: n }, (_, i) => i + 1), 5);

    // 주어진 조합과 시도한 배열을 비교하여 맞춘 개수 반환
    function countMatches(secret, attempt) {
        let count = 0;
        let i = 0, j = 0;

        while (i < secret.length && j < attempt.length) {
            if (secret[i] === attempt[j]) {
                count++;
                i++;
                j++;
            } else if (secret[i] < attempt[j]) {
                i++;
            } else {
                j++;
            }
        }
        return count;
    }

    // 모든 가능한 조합에 대해 확인
    for (const combination of allCombinations) {
        let isValid = true;

        for (let i = 0; i < q.length; i++) {
            const matchCount = countMatches(combination, q[i]);
            if (matchCount !== ans[i]) {
                isValid = false;
                break;
            }
        }

        if (isValid) {
            candidates.push(combination);
        }
    }

    // 가능한 경우의 수 개수를 반환
    return candidates.length;
}

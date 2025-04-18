function solution(numbers, target) {
    let count = 0;

    function explore(index, currentSum) {
        // 모든 숫자를 다 사용한 경우
        if (index === numbers.length) {
            // 목표값에 도달했으면 ++
            if (currentSum === target) {
                count++;
            }
            return;
        }

        // 현재 숫자를 더하는 경우
        let plus = currentSum + numbers[index];
        explore(index + 1, plus);

        // 현재 숫자를 빼는 경우
        let minus = currentSum - numbers[index];
        explore(index + 1, minus);
    }

    // 0번째 숫자부터 시작, 초기 합은 0
    explore(0, 0);

    return count;
}

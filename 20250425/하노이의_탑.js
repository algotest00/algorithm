function solution(n) {
    const answer = [];

    function hanoi(n, from, to, via) {
        if (n === 1) {
            answer.push([from, to]);
            return;
        }

        // 1. n-1개를 보조 기둥(via)로
        hanoi(n - 1, from, via, to);
        // 2. 가장 큰 원판을 목적지로
        answer.push([from, to]);
        // 3. 보조 기둥에서 목적지로
        hanoi(n - 1, via, to, from);
    }

    hanoi(n, 1, 3, 2); // 1에서 3으로, 2를 보조 기둥으로
    return answer;
}
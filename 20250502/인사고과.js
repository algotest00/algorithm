function solution(scores) {
    const wanho = scores[0];

    // 근무 태도 내림차순, 동료 평가 오름차순 정렬
    scores.sort((a, b) => {
        if (a[0] === b[0]) return a[1] - b[1];
        return b[0] - a[0];
    });

    let maxPeer = 0;
    const eligible = [];

    for (let [a, b] of scores) {
        // 완호보다 둘 다 큰 사람 있으면 탈락
        if (a > wanho[0] && b > wanho[1]) return -1;
        // 살아남는 조건
        if (b >= maxPeer) {
            eligible.push(a + b);
            maxPeer = Math.max(maxPeer, b);
        }
    }

    const wanhoSum = wanho[0] + wanho[1];
    eligible.sort((a, b) => b - a);

    let rank = 1;
    let count = 1;

    for (let i = 0; i < eligible.length; i++) {
        if (eligible[i] === wanhoSum) return rank;
        if (i < eligible.length - 1 && eligible[i] === eligible[i + 1]) {
            count++;
        } else {
            rank += count;
            count = 1;
        }
    }

    return -1;
}

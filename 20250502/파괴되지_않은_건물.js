function solution(board, skill) {
    const N = board.length;
    const M = board[0].length;

    const effect = Array.from({ length: N + 1 }, () => Array(M + 1).fill(0));

    // 누적합 배열에 차등 적용
    for (let [type, r1, c1, r2, c2, degree] of skill) {
        const delta = type === 1 ? -degree : degree;

        effect[r1][c1] += delta;
        if (c2 + 1 < M) effect[r1][c2 + 1] -= delta;
        if (r2 + 1 < N) effect[r2 + 1][c1] -= delta;
        if (r2 + 1 < N && c2 + 1 < M) effect[r2 + 1][c2 + 1] += delta;
    }

    // 가로 누적합
    for (let i = 0; i < N; i++) {
        for (let j = 1; j < M; j++) {
            effect[i][j] += effect[i][j - 1];
        }
    }

    // 세로 누적합
    for (let j = 0; j < M; j++) {
        for (let i = 1; i < N; i++) {
            effect[i][j] += effect[i - 1][j];
        }
    }

    // 실제 적용해서 내구도 계산
    let count = 0;
    for (let i = 0; i < N; i++) {
        for (let j = 0; j < M; j++) {
            if (board[i][j] + effect[i][j] > 0) {
                count++;
            }
        }
    }

    return count;
}

function solution(maps) {
    const rows = maps.length;
    const cols = maps[0].length;
    const visited = Array.from({ length: rows }, () => Array(cols).fill(false));
    const directions = [
        [1, 0], [-1, 0], [0, 1], [0, -1]
    ];
    const result = [];

    const bfs = (startY, startX) => {
        const queue = [[startY, startX]];
        let sum = parseInt(maps[startY][startX]);
        visited[startY][startX] = true;

        while (queue.length > 0) {
            const [y, x] = queue.shift();

            for (const [dy, dx] of directions) {
                const ny = y + dy;
                const nx = x + dx;

                if (
                    ny >= 0 && ny < rows &&
                    nx >= 0 && nx < cols &&
                    !visited[ny][nx] &&
                    maps[ny][nx] !== 'X'
                ) {
                    visited[ny][nx] = true;
                    sum += parseInt(maps[ny][nx]);
                    queue.push([ny, nx]);
                }
            }
        }

        return sum;
    };

    for (let y = 0; y < rows; y++) {
        for (let x = 0; x < cols; x++) {
            if (!visited[y][x] && maps[y][x] !== 'X') {
                result.push(bfs(y, x));
            }
        }
    }

    return result.length > 0 ? result.sort((a, b) => a - b) : [-1];
}

// 지게차와 크레인
function solution(storage, requests) {
    const maxRow = storage.length - 1;
    const maxColumn = storage[0].length - 1;

    let containerCnt = storage.length * storage[0].length;
    let storageMap = storage.map(row => row.split(""));

    // 주어진 위치가 창고 범위 내에 있는지 확인용
    const isInside = (r, c) => r >= 0 && r <= maxRow && c >= 0 && c <= maxColumn;

    // 주어진 위치가 외부에 노출되어 있는지 확인용
    const isOutside = (row, column) => {
        const dr = [0, 0, 1, -1]; // (우, 좌, 하, 상)
        const dc = [1, -1, 0, 0];

        for (let i = 0; i < 4; ++i) {
            let [nearR, nearC] = [row + dr[i], column + dc[i]];

            // 창고 경계를 벗어나면 외부로 노출된 상태
            if (nearR < 0 || nearR > maxRow || nearC < 0 || nearC > maxColumn) {
                return true;
            }
            // 인접한 위치가 true(외부와 연결된 빈 공간)라면 외부로 노출된 상태
            else if (isInside(nearR, nearC) && storageMap[nearR][nearC] === true) {
                return true;
            }
        }
        return false; // 외부와 연결되지 않은 경우
    };

    // 외부에서 접근 가능한 빈 공간을 모두 표시하는 함수
    const resetOutside = () => {
        for (let r = 0; r <= maxRow; ++r) {
            for (let c = 0; c <= maxColumn; ++c) {
                if (isOutside(r, c) && storageMap[r][c] === false) {
                    let queue = [[r, c]];

                    // BFS를 사용하여 외부와 연결된 빈 공간을 모두 표시
                    while (queue.length > 0) {
                        const [row, column] = queue.shift();
                        storageMap[row][column] = true; // 외부 접근 가능 표시

                        const dr = [0, 0, 1, -1];
                        const dc = [1, -1, 0, 0];

                        for (let i = 0; i < 4; ++i) {
                            const newRow = row + dr[i];
                            const newCol = column + dc[i];

                            // 외부와 연결된 빈 공간을 찾으면 큐에 추가
                            if (isInside(newRow, newCol) && isOutside(newRow, newCol) && storageMap[newRow][newCol] === false) {
                                queue.push([newRow, newCol]);
                            }
                        }
                    }
                }
            }
        }
    };

    requests.forEach((request) => {
        const alphabet = request[0];
        resetOutside(); // 외부 접근 가능한 상태 초기화

        if (request.length === 1) { // 단일 문자일 때
            let pickContainers = [];

            // 외부 접근 가능한 해당 알파벳을 모두 찾아 리스트에 저장
            for (let r = 0; r <= maxRow; ++r) {
                for (let c = 0; c <= maxColumn; ++c) {
                    if (isOutside(r, c) && alphabet === storageMap[r][c]) {
                        pickContainers.push([r, c]);
                    }
                }
            }

            // 찾은 컨테이너들을 제거하고 카운트를 감소시킴
            pickContainers.forEach(([r, c]) => {
                containerCnt--;
                storageMap[r][c] = true; // 빈 공간으로 설정
            });

        } else { // 문자 두 개일때
            for (let r = 0; r <= maxRow; ++r) {
                for (let c = 0; c <= maxColumn; ++c) {
                    if (storageMap[r][c] === alphabet) {
                        containerCnt--;
                        storageMap[r][c] = false; // 외부와 연결되지 않은 빈 공간으로 설정
                    }
                }
            }
        }
    });

    return containerCnt;
}

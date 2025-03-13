// 택배상자 꺼내기
const solution = (n, w, num) => {
    const arr = [];
    let index = 1;

    // 1. 요소를 2차원 배열로 만들기
    while (index <= n) {
        const subArr = [];
        for (let i = 0; i < w; i++) {
            if (index <= n) {
                subArr.push(index++);
            } else {
                subArr.push(0);
            }
        }
        arr.push(subArr);
    }

    // 2. 홀수번째 배열 요소는 역순으로 뒤집기
    for (let i = 0; i < arr.length; i++) {
        if (i % 2 !== 0) {
            arr[i].reverse();
        }
    }

    // 3. num의 위치 찾기
    let row = -1, col = -1;
    for (let i = 0; i < arr.length; i++) {
        for (let j = 0; j < arr[i].length; j++) {
            if (arr[i][j] === num) {
                row = i;
                col = j;
                break;
            }
        }
        if (row !== -1) break; // 찾았으면 종료
    }

    // 4. 택배상자를 얼마나 꺼내야하는지 찾기
    // -> 만일 마지막 행의 col 요소가 0 일 경우 -1 해준다.
    if (arr[arr.length - 1][col] === 0) {
        return arr.length - row - 1;
    } else {
        return arr.length - row;
    }
}
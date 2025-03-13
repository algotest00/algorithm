/* indexOf를 활용한 짝꿍찾기 
*   -> 시간초과 발생
*/
const solution = (X, Y) => {
    // 1. X와 Y를 배열로 변환
    let xList = X.split('');
    let yList = Y.split('');
    let result = [];

    // 2. X의 각 숫자가 Y에서 존재하는지 확인하고, 
    // -> 공통값을 찾아 result에 추가
    for (let i = 0; i < xList.length; i++) {
        const digit = xList[i];
        const index = yList.indexOf(digit);
        if (index !== -1) {
            result.push(digit);
            yList.splice(index, 1);  // Y에서 해당 숫자 제거
        }
    }

    // 3. 공통 숫자가 없으면 -1 반환
    if (result.length === 0) {
        return "-1";
    }

    // 4. 공통 숫자들이 0만 있는 경우 0 반환
    if (result.every(digit => digit === '0')) {
        return "0";
    }

    // 5. 숫자들을 내림차순으로 정렬하여 반환
    result.sort((a, b) => b - a);

    return result.join('');
}

/* 숫자 count를 활용한 짝꿍찾기 */
const solution2 = (X, Y) => {
    // 1. X와 Y의 숫자 빈도 계산
    let xCount = Array(10).fill(0);
    let yCount = Array(10).fill(0);

    // 2. X와 Y의 각 숫자의 빈도를 기록
    X.split('').forEach(digit => xCount[digit]++);
    Y.split('').forEach(digit => yCount[digit]++);

    let result = [];

    // 3. 공통되는 숫자들을 result 배열에 추가
    for (let i = 9; i >= 0; i--) {
        const commonCount = Math.min(xCount[i], yCount[i]);
        for (let j = 0; j < commonCount; j++) {
            result.push(i);
        }
    }

    // 4. 공통 숫자가 없으면 -1 반환
    if (result.length === 0) {
        return "-1";
    }

    // 5. 공통 숫자들이 0만 있는 경우 0 반환
    if (result.every(digit => digit === 0)) {
        return "0";
    }

    // 6. 결과 배열을 문자열로 반환
    return result.join('');
}

// TEST
console.log(solution2('3403', '13203'));
console.log(solution2('5525', '1255'));
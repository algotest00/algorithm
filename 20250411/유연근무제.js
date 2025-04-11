function solution(schedules, timelogs, startday) {
    const n = schedules.length;
    let count = 0;

    for (let i = 0; i < n; i++) {
        const hopeTime = schedules[i]; // 출근 희망 시각
        const deadline = addTenMinutes(hopeTime); // 출근 인정 시각

        let success = true;

        for (let j = 0; j < 7; j++) {
            const day = (startday + j - 1) % 7 + 1; // 실제 요일 (1~7)
            const time = timelogs[i][j];

            if (day >= 6) continue; // 토, 일은 무시

            if (time > deadline) {
                success = false;
                break;
            }
        }

        if (success) count++;
    }

    return count;
}

// 출근 희망 시각에 10분 더하는 함수
function addTenMinutes(time) {
    let hour = Math.floor(time / 100);
    let minute = time % 100;

    minute += 10;
    if (minute >= 60) {
        hour += 1;
        minute -= 60;
    }

    return hour * 100 + minute;
}

function solution(players, m, k) {
    let servers = Array(24).fill(0);
    let answer = 0;

    // 각 시간대(players 배열 인덱스 기준) 순회
    players.forEach((player, sTime) => {
        // 현재 시각에서 필요한 서버 수 = 사용자 수 / 서버당 수용 인원
        let required = Math.floor(player / m);

        // 현재 시간에 운영 중인 서버 수보다 부족하면 증설 필요
        if (required > servers[sTime]) {
            let need = required - servers[sTime];

            // 증설된 서버는 sTime ~ sTime + k - 1 시간까지 운영됨
            for (let i = 0; i < k; i++) {
                let targetTime = sTime + i;
                if (targetTime > 23) break;
                servers[targetTime] += need;
            }

            // 증설 횟수 ++
            answer += need;
        }
    });

    return answer;
}

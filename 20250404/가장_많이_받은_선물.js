function solution(friends, gifts) {
    const n = friends.length;

    // 1. 친구 이름을 인덱스로 매핑하기 위한 Map 생성
    const nameToIndex = new Map();
    friends.forEach((name, idx) => nameToIndex.set(name, idx));

    // 2. giftCount[i][j] = i가 j에게 선물한 횟수 저장용 2차원 배열
    const giftCount = Array.from({ length: n }, () => Array(n).fill(0));

    // 3. 각 친구의 선물 지수 계산용 배열
    const score = Array(n).fill(0); // 선물 지수 = 준 선물 수 - 받은 선물 수

    // 4. 다음 달에 받을 선물 수를 저장하는 배열
    const nextGifts = Array(n).fill(0);

    // 5. 선물 내역을 순회하면서 주고받은 기록 반영
    for (const gift of gifts) {
        const [from, to] = gift.split(" ");
        const fromIdx = nameToIndex.get(from);
        const toIdx = nameToIndex.get(to);

        // from → to 로 선물한 횟수 증가
        giftCount[fromIdx][toIdx]++;

        // 선물 지수 갱신: from은 +1, to는 -1
        score[fromIdx]++;
        score[toIdx]--;
    }

    // 6. 모든 친구 쌍에 대해 다음 달 선물 계산
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < n; j++) {
            if (i === j) continue; // 자기 자신 제외

            const give = giftCount[i][j];
            const take = giftCount[j][i];

            // 선물을 더 많이 준 사람이 다음 달에 받음
            if (give > take) {
                nextGifts[i]++;
            }
            // 주고받은 수가 같으면 선물 지수 비교
            else if (give === take) {
                if (score[i] > score[j]) {
                    nextGifts[i]++;
                }
            }
        }
    }

    // 7. 선물개수반환
    return Math.max(...nextGifts);
}

function solution(coin, cards) {
    const n = cards.length;
    const targetSum = n + 1;

    const hand = Array(n + 1).fill(false); // 손에 있는 카드
    const pool = Array(n + 1).fill(false); // 코인으로 가져올 수 있는 카드
    const deck = [...cards]; // 카드 더미

    // 처음에 n/3 장을 손에 넣음
    for (let i = 0; i < n / 3; i++) {
        hand[deck.shift()] = true;
    }

    let round = 1;

    while (deck.length >= 2) {
        const card1 = deck.shift();
        const card2 = deck.shift();
        pool[card1] = true;
        pool[card2] = true;

        let played = false;

        // 1. 손에 있는 카드로만 조합
        for (let i = 1; i <= n / 2; i++) {
            const pair = targetSum - i;
            if (hand[i] && hand[pair] && i !== pair) {
                hand[i] = false;
                hand[pair] = false;
                played = true;
                break;
            }
        }
        if (played) {
            round++;
            continue;
        }

        // 2. 가지고 있는 카드 + 들고올 수 있는 카드 조합 (코인 1개)
        for (let i = 1; i <= n / 2; i++) {
            const pair = targetSum - i;

            if (coin >= 1 && hand[i] && pool[pair] && i !== pair) {
                hand[i] = false;
                pool[pair] = false;
                coin--;
                played = true;
                break;
            }

            if (coin >= 1 && pool[i] && hand[pair] && i !== pair) {
                pool[i] = false;
                hand[pair] = false;
                coin--;
                played = true;
                break;
            }
        }
        if (played) {
            round++;
            continue;
        }

        // 3. 가지고 올 수 있는 카드 조합 (코인 2개)
        for (let i = 1; i <= n / 2; i++) {
            const pair = targetSum - i;
            if (coin >= 2 && pool[i] && pool[pair] && i !== pair) {
                pool[i] = false;
                pool[pair] = false;
                coin -= 2;
                played = true;
                break;
            }
        }

        if (played) {
            round++;
        } else {
            break; // 어떤 조합도 없으면 게임 종료
        }
    }

    return round;
}

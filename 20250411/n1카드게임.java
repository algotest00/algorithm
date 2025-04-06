import java.util.*;
import java.util.stream.*;

class Solution {
    int rcoin;
    int n;
    boolean[] hands;
    boolean[] takes;

    public int solution(int coin, int[] cards) {
        // 초기화
        rcoin = coin;
        n = cards.length;
        hands = new boolean[n + 1];
        takes = new boolean[n + 1];

        Queue<Integer> queue = Arrays.stream(cards).boxed().collect(Collectors.toCollection(LinkedList::new));
        // 1. 처음 카드 n/3 장을 뽑아 모두 가짐
        for (int i = 1; i <= n / 3; i++) hands[queue.poll()] = true;

        // 2. 1라운드부터 시작
        int round = 1;
        while (!queue.isEmpty()) {
            // 카드 2장 뽑기
            takes[queue.poll()] = true;
            takes[queue.poll()] = true;

            // 제출할게 있으면 코인 및 카드 제출
            if (!cardOut()) break;

            round++;
        }

        return round;
    }

    boolean cardOut() {
        // hands 2개이면 코인 제출 X
        for (int i = 1; i <= n / 2; i++) {
            if (hands[i] && hands[n + 1 - i]) {
                hands[i] = false;
                hands[n + 1 - i] = false;
                System.out.println(String.format("%d, %d", i, n + 1 - i));
                return true;
            }
        }

        // hands 1개 outs 1개이면 코인 1개 제출
        for (int i = 1; i <= n / 2; i++) {
            if (rcoin >= 1 && hands[i] && takes[n + 1 - i]) {
                hands[i] = false;
                takes[n + 1 - i] = false;
                rcoin -= 1;
                System.out.println(String.format("%d, %d", i, n + 1 - i));
                return true;
            }

            if (rcoin >= 1 && takes[i] && hands[n + 1 - i]) {
                takes[i] = false;
                hands[n + 1 - i] = false;
                rcoin -= 1;
                System.out.println(String.format("%d, %d", i, n + 1 - i));
                return true;
            }
        }

        // outs 2개이면 코인 2개 제출
        for (int i = 1; i <= n / 2; i++) {
            if (rcoin >= 2 && takes[i] && takes[n + 1 - i]) {
                takes[i] = false;
                takes[n + 1 - i] = false;
                rcoin -= 2;
                System.out.println(String.format("%d, %d", i, n + 1 - i));
                return true;
            }
        }

        return false;
    }
}
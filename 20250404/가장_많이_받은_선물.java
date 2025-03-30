import java.util.*;
import java.util.stream.*;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        // 선물 히스토리 생성
        Map<String, GiftHistory> history = Arrays.stream(friends)
                .collect(Collectors.toMap(s -> s, GiftHistory::new));

        for (String gift : gifts) {
            String[] split = gift.split(" ");
            String sender = split[0];
            String receiver = split[1];

            history.get(sender).addSendHistory(receiver);
            history.get(receiver).addReceiveHistory(sender);
        }

        Map<String, Integer> pred = Arrays.stream(friends)
                .collect(Collectors.toMap(s -> s, s -> 0));
        // 탐색
        for (int i = 0; i < friends.length - 1; i++) {
            GiftHistory o1 = history.get(friends[i]);
            for (int j = i + 1; j < friends.length; j++) {
                GiftHistory o2 = history.get(friends[j]);
                // compare < 0 : o1이 선물을 보내야 함
                // compare > 0 : o2가 선물을 보내야 함
                int compare = o1.compareTo(o2);

                if (compare < 0) pred.compute(o2.name, (k, v) -> v + 1);
                if (compare > 0) pred.compute(o1.name, (k, v) -> v + 1);
            }
        }

        return pred.values().stream().mapToInt(Integer::valueOf).max().orElse(0);
    }

    class GiftHistory implements Comparable<GiftHistory> {
        String name;
        Map<String, Integer> sendHistory = new HashMap<>();
        Map<String, Integer> receiveHistory = new HashMap<>();

        GiftHistory(String name) {
            this.name = name;
        }

        void addSendHistory(String friend) {
            if (sendHistory.computeIfPresent(friend, (k, v) -> v + 1) == null) {
                sendHistory.computeIfAbsent(friend, k -> 1);
            }
        }

        void addReceiveHistory(String friend) {
            if (receiveHistory.computeIfPresent(friend, (k, v) -> v + 1) == null) {
                receiveHistory.computeIfAbsent(friend, k -> 1);
            }
        }

        int getGiftScore() {
            return sendHistory.values().stream().mapToInt(Integer::valueOf).sum()
                    - receiveHistory.values().stream().mapToInt(Integer::valueOf).sum();
        }

        // return 0 : 선물 서로 안보냄
        // return < 0 : this가 선물 보냄
        // return > 0 : o가 선물 보냄
        @Override
        public int compareTo(GiftHistory o) {
            int compare = this.sendHistory.getOrDefault(o.name, 0)
                    .compareTo(o.sendHistory.getOrDefault(this.name, 0));

            if (compare != 0) return compare;
            return this.getGiftScore() - o.getGiftScore();
        }
    }
}
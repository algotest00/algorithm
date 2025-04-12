import java.util.*;

class Solution {
    public int solution(int[] players, int m, int k) {
        int answer = 0;

        Queue<Server> queue       = new LinkedList<>();
        int           serverCount = 0;

        for (int time = 0; time < 24; time++) {
            // 만료 서버등 지우기
            while (queue.peek() != null && queue.peek().exp <= time) {
                serverCount -= queue.poll().count;
            }

            // 현재 서버들로 인원수 감당 가능한지 확인
            int requiredCount = players[time] / m;
            if (requiredCount > serverCount) {
                // 감당 안되면 필요수만큼 증설
                int plusCount = requiredCount - serverCount;
                queue.add(new Server(time + k, plusCount));
                serverCount += plusCount;
                answer += plusCount;
            }

        }

        return answer;
    }

    class Server {
        int exp;
        int count;

        Server(int exp, int count) {
            this.exp = exp;
            this.count = count;
        }
    }
}

import java.util.*;
/**
 * [알고리즘 설명]
 * - 매 시점마다 플레이어 수에 따라 필요한 서버 수를 계산함.
 * - PriorityQueue(최소 힙)를 이용해, 서버의 사용 가능 여부(startTime, endTime 기준)를 확인함.
 * - 서버는 사용 시간이 지나면 제거하고, 사용 가능한 서버는 재사용.
 * - 부족할 경우 필요한 수만큼 서버를 증설(addServer).
 * 
 * [핵심 로직]
 * - 시간 기준으로 서버를 정렬하여 가장 먼저 사용 가능한 서버부터 할당.
 * - 서버는 고정된 시간(k) 동안만 사용 가능하며, 사용 시간이 지나면 큐에서 제외됨.
 * - 매 시점마다 필요한 서버 수만큼 사용 가능한 서버를 할당하거나 증설함.
 */
class Solution {
    PriorityQueue<Server> pq = new PriorityQueue<>(); // 현재 사용 가능한 서버들
    PriorityQueue<Server> tempPq = new PriorityQueue<>(); // 사용 가능한 서버를 임시 저장

    public int solution(int[] players, int m, int k) {
        int answer = 0;

        for (int i = 0; i < players.length; i++) {
            int requiredServerCount = players[i] / m;

            if (pq.isEmpty()) {
                answer += addServer(requiredServerCount, i, k);
            } else {
                answer += assignServers(requiredServerCount, i, k);
            }
        }

        return answer;
    }
    
    /*
     * 필요한 서버 수만큼 기존 서버 할당을 시도하고, 부족할 경우 증설
     */
    private int assignServers(int requiredCount, int currentTime, int duration) {
        int addedServerCount = 0;

        while (requiredCount > 0) {
            if (!pq.isEmpty()) {
                Server server = pq.poll();
                if (server.canUseAtTime(currentTime)) {
                    tempPq.offer(server);
                    requiredCount--;
                }
            } else {
                addedServerCount += addServer(requiredCount, currentTime, duration);
                break;
            }
        }

        while (!tempPq.isEmpty()) { //서버다시 원복
            pq.offer(tempPq.poll());
        }

        return addedServerCount;
    }

    private int addServer(int count, int time, int k) {
        for (int i = 0; i < count; i++) {
            pq.offer(new Server(time, time + k));
        }
        return count;
    }

    static class Server implements Comparable<Server> {
        private int startTime;
        private int endTime;

        public Server(int startTime, int endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public boolean canUseAtTime(int time) {
            return time < this.endTime;
        }

        @Override
        public int compareTo(Server other) {
            return Integer.compare(this.startTime, other.startTime);
        }

        @Override
        public String toString() {
            return "startTime: " + startTime + ", endTime: " + endTime;
        }
    }
}

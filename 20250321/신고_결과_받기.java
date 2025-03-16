import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 유저가 신고한 ID들
        Map<String, Set<String>> reportingMap = Arrays.stream(id_list)
                .collect(Collectors.toMap(s -> s, s -> new HashSet<String>()));

        // 유저를 신고한 ID들
        Map<String, Set<String>> reportedMap = Arrays.stream(id_list)
                .collect(Collectors.toMap(s -> s, s -> new HashSet<String>()));

        for (String s : report) {
            String[] split = s.split(" ");
            String reporterId = split[0];
            String targetId = split[1];

            // reporter가 신고한 ID 누적
            reportingMap.get(reporterId).add(targetId);
            // target을 신고한 ID 누적
            reportedMap.get(targetId).add(reporterId);
        }

        List<Integer> result = new ArrayList<>();
        for (String userId : id_list) {
            int num = 0;
            for (String targetId: reportingMap.get(userId)) {
                if (reportedMap.get(targetId).size() >= k) num++;
            }
            result.add(num);
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }
}
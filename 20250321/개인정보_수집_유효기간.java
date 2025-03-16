import java.util.*;
import java.util.stream.*;

class Solution {
    // today YYYY.MM.DD
    // terms ["A 3", "B 100", ...]
    // privacies ["YYYY.MM.DD A", "YYYY.MM.DD B", ...]
    public int[] solution(String today, String[] terms, String[] privacies) {
        // 1. terms >> `Map<종류, 유효기간>`로 변환
        Map<String, Integer> termsMap = Arrays.stream(terms)
                .collect(Collectors.toMap(s -> s.split(" ")[0], s -> Integer.valueOf(s.split(" ")[1])));

        // 2. privacies >> `List<종료일자>`로 변환
        List<String> expirations = Arrays.stream(privacies).map(s -> {
            String[] privacy = s.split(" ");
            String writeAt = privacy[0];
            String type = privacy[1];

            return getExpiration(writeAt, termsMap.get(type));
        }).collect(Collectors.toList());

        // 3. `List<종료일자>.filter(exp -> today > exp)` 갯수 반환
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < expirations.size(); i++) {
            if (today.compareTo(expirations.get(i)) > 0) answer.add(i + 1);
        }

        return answer.stream().mapToInt(Integer::intValue).toArray();
    }

    /**
     * @param date YYYY.MM.DD
     * @param month type int
     */
    private String getExpiration(String date, int month) {
        String[] s = date.split("\\.");
        int y = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int d = Integer.parseInt(s[2]) - 1;

        if (d == 0) {
            m--;
            d = 28;
        }

        if (m == 0) {
            y--;
            m = 12;
        }

        m += month;
        if (m > 12) {
            // m이 12 배수일 수 있으므로 -1 처리 후 계산
            y = y + ((m - 1) / 12);
            m = (m - 1) % 12 + 1;
        }

        return String.format("%d.%02d.%02d", y, m, d);
    }
}
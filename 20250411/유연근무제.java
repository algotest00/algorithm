import java.util.*;
import java.time.*;
import java.time.format.*;

class Solution {
    final DateTimeFormatter hhmmFormatter = DateTimeFormatter.ofPattern("HHmm");
    final DateTimeFormatter hmmFormatter = DateTimeFormatter.ofPattern("hmm");

    public int solution(int[] schedules, int[][] timelogs, int startday) {
        // 주말 인덱스
        // 월요일(1)일시, 인덱스 5, 6이 주말
        // 화요일(2)일시, 인덱스 4, 5가 주말
        // 일요일(7)일시, 인덱스 0, 6이 주말
        final int holyIndex1 = (6 - startday + 7) % 7;
        final int holyIndex2 = (7 - startday + 7) % 7;

        List<Work> works = new ArrayList<>();
        for (int i = 0; i < timelogs.length; i++) {
            works.add(new Work(schedules[i], timelogs[i]));
        }

        return (int) works.stream().filter(work -> work.isNotLate(holyIndex1, holyIndex2)).count();
    }

    class Work {
        int schedule;
        int[] timelogs;

        Work(int schedule, int[] timelogs) {
            this.schedule = schedule;
            this.timelogs = timelogs;
        }

        boolean isNotLate(int holyIndex1, int holyIndex2) {
            // 출근 가능 시각
            int dtime = Integer.parseInt(
                    LocalTime.parse(String.format("%04d", schedule), hhmmFormatter)
                            .plusMinutes(10)
                            .format(hmmFormatter)
            );

            for (int d = 0; d < 7; d++) {
                if (d == holyIndex1 || d == holyIndex2) continue;
                if (timelogs[d] > dtime) return false;
            }

            return true;
        }
    }
}
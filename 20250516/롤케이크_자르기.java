import java.util.*;
import java.util.stream.*;

class Solution2 {
    public int solution(int[] topping) {
        int answer = 0;

        Map<Integer, Integer> map = Arrays.stream(topping).boxed().collect(Collectors.toMap(i -> i, i -> 1, Integer::sum));
        Set<Integer> set = new HashSet<>();

        for (int t : topping) {
            set.add(t);
            Integer v = map.compute(t, (key, value) -> --value);
            if (v == 0) map.remove(t);
            if (set.size() > map.size()) break;
            if (set.size() == map.size()) answer++;
        }

        return answer;
    }
}
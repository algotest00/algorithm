import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* ※ 신고 -> 리포트
 * 1. 사용자가 신고한 목록 -> reportMap ("A", ["B", "C",..])
 * 2. 사용자가 신고당한 기록 -> countMap ("B", 1)
 * 3. report배열에서 from이 to를 신고한 목록을 카운트한 다음 countMap에 to의 신고당한 횟수 기록.
 * 4. reportMap에서 신고한 유저가 리포트 당했을 경우, answer에 기록록
 */
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, Set<String>> reportMap = new HashMap<>(); //사용자가 신고한 목록
        Map<String, Integer> countMap = new HashMap<>(); //사용자 신고당한 횟수 기록
        //1. 사용자가 신고한 목록 초기화
        for(int i = 0; i < id_list.length; i++) {
            reportMap.put(id_list[i], new HashSet<>());
            countMap.put(id_list[i], 0);
        }
        
        //2. 신고 갱신
        for(int i = 0 ; i < report.length; i++) {
            String[] s = report[i].split(" ");
            String from = s[0];
            String to = s[1];
            
            //from의 신고 목록에 to 추가하기(최초 신고 한정)
            if(!reportMap.get(from).contains(to)){
                reportMap.get(from).add(to);    
                //to의 신고 당한 횟수 갱신하기
                countMap.put(to, countMap.get(to)+1);
            }
        }
        
        //3. 신고한사람이 제재 대상인지 확인하기
        for(String key : countMap.keySet()){
            if(countMap.get(key) >= k){
                for(int i = 0; i < id_list.length; i++){
                    if(reportMap.get(id_list[i]).contains(key)){
                        answer[i]++;
                    }
                }
            }
        }
        
        return answer;
    }
}
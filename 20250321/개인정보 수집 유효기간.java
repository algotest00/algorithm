
import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Solution {
    public int[] solution(String today, String[] terms, String[] privacies) {
        List<Integer> result = new ArrayList<>();
        
        // 약관 종류와 유효기간을 매핑
        Map<String, Integer> type = new HashMap<>();
        for (String s : terms) {
            String[] term = s.split(" ");
            type.put(term[0], Integer.parseInt(term[1]));
        }
        
        // 오늘 날짜 처리
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
        LocalDate dateToday = LocalDate.parse(today, formatter);

        // 개인정보 처리
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            LocalDate startDt = LocalDate.parse(privacy[0], formatter);
            String ptype = privacy[1];
            
            // 해당 약관에 맞는 유효기간을 구함
            int period = type.get(ptype);
            
            // 유효기간 종료 날짜 계산
            LocalDate expirationDate = startDt.plusMonths(period);  // 유효기간이 끝나는 날짜는 포함 안됨
            
            // expirationDate가 today와 같거나 이전이면 파기해야 함
            if (!expirationDate.isAfter(dateToday)) {
                result.add(i + 1); // 개인정보 번호는 1부터 시작하므로, i + 1
            }
        }

        // 결과 리스트를 배열로 변환
        int[] answer = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            answer[i] = result.get(i);
        }

        return answer;
    }
}





// 틀린코드
// import java.util.*;
// import java.time.LocalDate;  
// import java.time.format.DateTimeFormatter;  
// import java.time.Period; 


// class Solution {
//     public int[] solution(String today, String[] terms, String[] privacies) {
 
//         List<Integer> result = new ArrayList<>();
        
//         // type key = 약관종류, value = 약관개월수 
//         Map<String, Integer> type = new HashMap<>();
//         for(String s : terms){
//             type.put(s.split(" ")[0],Integer.parseInt(s.split(" ")[1]));
//         }
        
        
//         Map<String, Integer> privaciesMap = new HashMap<>();
//         for(String t : privacies ){
//             String startDt = t.split(" ")[0];
//             String ptype = t.split(" ")[1];
//             privaciesMap.put(startDt, type.get(ptype));
//         }
        
//         //날짜형태 변형
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
//         LocalDate dateToday = LocalDate.parse(today, formatter);  
        
//         //today 와 privaciesMap에 있는 startDt 와 비교
//         for (Map.Entry<String, Integer> entry : privaciesMap.entrySet()) {
//             int i = 1;
//             LocalDate startDt = LocalDate.parse(entry.getKey(), formatter);
//             Integer month = entry.getValue(); // 값


//             // 월을 더하기 
//             LocalDate newDate = startDt.plusMonths(month);
            
//             if(!dateToday.isBefore(newDate)){
//                 result.add(i);
//             }
//             i++;
            
//         }
 
//         int[] answer = new int[result.size()];
//         for (int i = 0; i < result.size(); i++) {
//             answer[i] = result.get(i);
//         }

//         return answer;
//     }
// }
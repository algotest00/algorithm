import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
 
        // id_map <String, Set> <신고자, 신고한사람들>
        Map<String, Set<String>> id_map = new HashMap<>();
         //set에 id  넣어주기
        for(String name : id_list){
            id_map.put(name, new HashSet<>());
        }
        
        
        // 중복 신고건 없애기 위해 report set 형태로 만들기
        Set<String> set_report = new HashSet<>(Arrays.asList(report));
        
        //타겟이 얼마나 신고당햇는지 확인용
         Map<String, Integer> target_map = new HashMap<>();
       
         
        // 리포트 돌면서 공백 기준으로 신고자, 타깃 나눠서 누가 누굴 신고했는지 id_map 에 담아주기
        for(String s : set_report){
            String reporter = s.split(" ")[0];
            String target = s.split(" ")[1];
            
            id_map.get(reporter).add(target);
            // 없을 경우 default로 0 아닐경우 value++
            target_map.put(target, target_map.getOrDefault(target, 0) + 1);

        }
        
        List<Integer> result = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : target_map.entrySet()) {
            //신고당한 타겟이 k회 이상 신고되었을 시
            if(entry.getValue() >= k){
                String target = entry.getKey();
                // 신고한 사람들(id_map에서 해당 타겟을 신고한 사람들을 찾기)
                for (String reporter : id_map.keySet()) {
                    // 해당 타겟을 신고한 사람이면 메일을 보냈다고 계산
                    if (id_map.get(reporter).contains(target)) {
                        answer[Arrays.asList(id_list).indexOf(reporter)]++;
                    }
                }
                
            }
        }
           
         return answer;
    }
}


        
 // 실패한 방법 => 너무 for문을 많이 돌아야함       
//         // 1. 리포트에서 중복 제거 => 같은사람에 대해서 한번만 신고할 수 있으므로
//         report = Arrays.stream(report).distinct().toArray(String[]::new);
        
//          // 2. Id_List를 맵에 담아서 key Value 형태로 신고당한횟수가 나올때마다 Value 값 증가시키기
//         Map<String, Integer> id_map = new HashMap<>();
//         int id_list_length = id_list.length;
//         for(int i =0; i< id_list_length; i++ ){
//             id_map.put(id_list[i],0);
//         }
        
        
// 3. 리포트에서 배열을 for문 돌면서 공백 기준으로 앞뒤로 나눈 후 신고당한사람을 key 로 갖고있는 맵 value 증가시키기
//         Set<String> dec_set = new HashSet<>();          
//         for(int i=0 ; i<report.length ;i++){
//             String dec = report[i].split(" ")[1];
//              id_map.put(dec, id_map.get(dec) + 1);
//             // 신고 횟수가 k 이상이면 중복 없이 추가
//             if (id_map.get(dec) >= k) {
//                 dec_set.add(dec);  
//             }
//             System.out.println(dec_set);
//         }
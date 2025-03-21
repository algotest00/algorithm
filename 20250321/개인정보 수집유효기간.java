//일자로 계산해서 비교하기

import java.util.ArrayList;
import java.util.HashMap;

/*
 * 1. 년과 월을 일자로 변환해서 계산하기 ex) 2021.05.02 -> 2021 * 12 * 28 + 05 * 28 + 28
 * 
 */
class Solution {
    public ArrayList<Integer> solution(String today, String[] terms, String[] privacies) {
        // int[] answer = {};
        ArrayList<Integer> answer = new ArrayList<>();
        HashMap<String, Integer> termMap = new HashMap<>();
        
        //약관의 유효기관 정리
        for(int i = 0 ; i < terms.length; i++){
            String[] temp = terms[i].split(" ");
            termMap.put(temp[0],Integer.parseInt(temp[1]));
        }
        String[] todaySplit = today.split("\\.");
        int todayValue = Integer.parseInt(todaySplit[0]) * 12 * 28 + Integer.parseInt(todaySplit[1]) * 28 + Integer.parseInt(todaySplit[2]);
        for(int i = 0; i < privacies.length; i++) {
            String[] temp = privacies[i].split(" ");
            String[] date = temp[0].split("\\.");
            String term = temp[1];
            
            int year = Integer.parseInt(date[0]) * 12 * 28;
            int month = Integer.parseInt(date[1]) * 28;
            int day = Integer.parseInt(date[2]);
            
            if(todayValue >= year + month + day + termMap.get(term)*28) answer.add(i+1);
        }
        return answer;
    }
}


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Solution2 {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        // 약관의 유효 기간을 저장하는 Map
        Map<String, Integer> termMap = Arrays.stream(terms)
            .map(term -> term.split(" "))
            .collect(Collectors.toMap(t -> t[0], t -> Integer.parseInt(t[1])));

        // today 날짜를 Integer 값으로 변환
        int todayValue = convertToDays(today);

        // privacies 배열을 순회하며 유효기간을 비교한 후, 인덱스를 리스트에 저장
        return IntStream.range(0, privacies.length)
                .filter(i -> {
                    String[] parts = privacies[i].split(" ");
                    int expirationDate = convertToDays(parts[0]) + termMap.get(parts[1]) * 28;
                    return todayValue >= expirationDate;
                })
                .mapToObj(i -> i + 1)
                .collect(Collectors.toList());
    }

    // 날짜 변환 메서드
    private int convertToDays(String date) {
        String[] parts = date.split("\\.");
        int year = Integer.parseInt(parts[0]) * 12 * 28;
        int month = Integer.parseInt(parts[1]) * 28;
        int day = Integer.parseInt(parts[2]);
        return year + month + day;
    }
}

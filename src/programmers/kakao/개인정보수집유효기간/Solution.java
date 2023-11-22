package programmers.kakao.개인정보수집유효기간;

import java.util.*;

class Solution {
    public List<Integer> solution(String today, String[] terms, String[] privacies) {
        Map<String, Integer> map = new HashMap<>();
        for (String term : terms) {
            String[] temp = term.split(" ");
            map.put(temp[0], Integer.parseInt(temp[1]) * 28);
        }

        int todayDay = getDay(today);
        List<Integer> answer = new ArrayList<>();
        for (int i = 0; i < privacies.length; i++) {
            String[] privacy = privacies[i].split(" ");
            int day = getDay(privacy[0]) + map.get(privacy[1]) - 1;
            if (day < todayDay) {
                answer.add(i + 1);
            }
        }

        return answer;
    }

    public int getDay(String date) {
        int year = Integer.parseInt(date.substring(0, 4)) * 12 * 28;
        int month = Integer.parseInt(date.substring(5, 7)) * 28;
        int day = Integer.parseInt(date.substring(8, 10));
        return year + month + day;
    }

    class Info {
        int day;
        int index;

        public Info(int day, int index) {
            this.day = day;
            this.index = index;
        }
    }

}
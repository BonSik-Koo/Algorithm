package programmers.L3.셔틀버스;

import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        PriorityQueue<Integer> people = new PriorityQueue<>((t1, t2) -> t1 - t2);
        for(String time : timetable) {
            people.add(convertToInt(time));
        }

        int start = convertToInt("09:00");
        int end = start + t * (n-1);
        for(int time=start; time<=end; time+=t) {
            // 마지막 버스인 경우
            if(time == end) {
                int count = 0;
                while(count < m-1 && !people.isEmpty() && people.peek() <= time) {
                    people.poll();
                    count++;
                }

                // 대기하는 크루가 한명도 없는 경우
                int result = 0;
                if(people.isEmpty()) {
                    result = time;
                }
                // 원래 탈수 있던 크루인 경우
                else if(people.peek() <= time) {
                    result = people.poll() - 1;
                }
                // 크루원 모두가 탈수 없던 경우
                else {
                    result = time;
                }
                answer = convertToString(result);
            }
            // 마지막 버스가 아닌 경우
            else {
                // 인원수만큼 태우기
                int count = 0;
                while(count < m && !people.isEmpty() && people.peek() <= time) {
                    people.poll();
                    count++;
                }
            }
        }

        return answer;
    }

    private int convertToInt(String time) {
        String[] arr = time.split(":");
        return Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
    }

    private String convertToString(int minute) {
        int hour = minute / 60;
        int min = minute % 60;
        return String.format("%02d", hour) + ":" + String.format("%02d", min);
    }
}
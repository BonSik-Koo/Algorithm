package programmers.L2;

import java.util.*;

/**
 * - 그리디(문자열 다루기)
 * - 혼자 푼 문제
 */
public class 주차요금계산 {

    public static int[] solution(int[] fees, String[] records) {

        Map<String , List<Integer>> map = new HashMap<>();
        Map<String, Boolean> check = new HashMap<>();

        for(String s : records) {
            String[] record = s.split(" ");
            String[] split = record[0].split(":");
            int m = Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);
            if(map.get(record[1]) == null) { //새로운 차량
                List<Integer> list = new ArrayList<>();  //사실상 사이즈2면 충분분
                list.add(m);
                map.put(record[1], list);
                check.put(record[1], false); //아직 출차하지 않음
                continue;
            }

            if(record[2].equals("OUT")) { //기존에 있는 차량이고 출차하는 경우
                List<Integer> time = map.get(record[1]);
                Integer startTime = time.get(time.size() - 1);
                time.remove(time.size()-1); //삭제
                int diff = m - startTime;

                if(time.size() == 1) { //한번 입차,출차를 한 차량
                    Integer preDiff = time.get(time.size() - 1);
                    time.remove(time.size() - 1);
                    time.add(preDiff + diff); //누적으로 이전 시간과 더해주기
                }else
                    time.add(diff);

                check.put(record[1], true);
            }else { //기존에 차량이고 입차하는 경우
                List<Integer> time = map.get(record[1]);
                time.add(m);
                check.put(record[1], false);
            }
        }

        Map<String, List<Integer>> sortMap = new TreeMap<>(map);
        int[] answer = new int[map.keySet().size()];
        int i =0;
        for(String kar : sortMap.keySet()) {
            List<Integer> time = sortMap.get(kar);

            int preDiff = 0;
            if(check.get(kar) == false) { //입고는 하였지만 출차를 하지 않은 경우 - 리스트 사이즈가 2임 !!! 사이즈가 2가 아닐수도 있음
                Integer startTime = time.get(time.size() - 1);
                time.remove(time.size()-1);
                if(time.size() == 1) {
                    preDiff = time.get(time.size() - 1);
                    time.remove(time.size() - 1);
                }
                time.add(preDiff + (1439-startTime));
            }

            if(time.get(time.size()-1) > fees[0]) { //기본 시간을 초과한 경우
                answer[i] = fees[1] + (int)Math.ceil((time.get(time.size()-1)-fees[0])/(double)fees[2]) * fees[3]; //!!!!!!!!!
            }else{
                answer[i] = fees[1];
            }
            i++;
        }
        return answer;
    }



    public static void main(String[] args){
        //int[] fees = {180, 5000, 10, 600};
        //String[] records = {"05:34 5961 IN", "06:00 0000 IN", "06:34 0000 OUT", "07:59 5961 OUT", "07:59 0148 IN", "18:59 0000 IN", "19:09 0148 OUT", "22:59 5961 IN", "23:00 5961 OUT"};

        //int[] fees = {120, 0, 60, 591};
        //String[] records = {"16:00 3961 IN","16:00 0202 IN","18:00 3961 OUT","18:00 0202 OUT","23:58 3961 IN"};

        //int[] fees = {1, 461, 1, 10};
        //String[] records = 	{"00:00 1234 IN"};

        int[] fees = {180, 5000, 10, 600};
        String[] records =  {"05:34 5961 IN", "06:34 5961 OUT", "07:34 5961 IN", "08:34 5961 OUT", "09:34 5961 IN", "10:34 5961 OUT", "11:34 5961 IN", "12:34 5961 OUT"};

        int[] solution = solution(fees, records);
        System.out.println(Arrays.toString(solution));
    }

}

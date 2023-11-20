package programmers.kakao.성격유형검사;
import java.util.*;

public class Solution {
    public String solution(String[] survey, int[] choices) {
        Map<Character, Integer> map = new HashMap<>();
        init(map);

        for(int i=0; i<survey.length; i++){
            String kind = survey[i];
            int choice = choices[i];

            // 각 성격 유형 점수 업데이트
            if(choice>=5){
                map.put(kind.charAt(1), map.get(kind.charAt(1)) + choice-4);
            }else if(choice<=3){
                map.put(kind.charAt(0), map.get(kind.charAt(0)) + -choice+4);
            }
        }

        String answer = getResult(map);
        return answer;
    }

    public String getResult(Map<Character, Integer> map){
        StringBuilder sb = new StringBuilder();
        if(map.get('R') >= map.get('T')){
            sb.append('R');
        }else{
            sb.append('T');
        }

        if(map.get('C') >= map.get('F')){
            sb.append('C');
        }else{
            sb.append('F');
        }

        if(map.get('J') >= map.get('M')){
            sb.append('J');
        }else{
            sb.append('M');
        }

        if(map.get('A') >= map.get('N')){
            sb.append('A');
        }else{
            sb.append('N');
        }

        return sb.toString();
    }

    public void init(Map<Character, Integer> map){
        map.put('R', 0);
        map.put('T', 0);
        map.put('C', 0);
        map.put('F', 0);
        map.put('J', 0);
        map.put('M', 0);
        map.put('A', 0);
        map.put('N', 0);
    }

}

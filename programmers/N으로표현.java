//DP 문제
import java.util.*;

class Solution {
    public int solution(int N, int number) {
        
        int answer = -1;
        List<Set<Integer>> list = new ArrayList<>(); //중복값은 하나만
        for(int i =0;i<=8; i++){
            list.add(new HashSet<>());
        }
        
        String repeat = String.valueOf(N);
        list.get(1).add(N);
        //DP 로직
        for(int i=2;i<9;i++){ //최대 8개까지 검증
            Set<Integer> addList = list.get(i);
            
            for(int j=1;j<i;j++){ 
                //ex) 4(i)-> 1-3, 2-2, 3-1 조합
                Set<Integer> preList = list.get(j);
                Set<Integer> postList = list.get(i-j);
                
                for(int pre : preList){
                    for(int post : postList){
                        addList.add(pre + post);
                        addList.add(pre - post);
                        addList.add(pre * post);
                        
                        if(pre!=0 && post!=0){
                            addList.add(pre/post);
                        }
                    }
                }
            }
            repeat += String.valueOf(N);
            addList.add(Integer.valueOf(repeat));
        }
        
        //정답 찾기
        for(int i=1;i<9;i++){
            Set<Integer> setList = list.get(i);
            if(setList.contains(number)){
                answer = i;
                break;
            }
        }
        
        return answer;
    }
}

//그리디 , 정렬 문제
import java.util.*;
import java.math.*;

class Solution {
    public int solution(int[][] scores) {
        int[] won = scores[0];//원호의 점수
        Arrays.sort(scores, new Comparator<int[]>(){ //근무태도 내림차순, 동료 평가 내림차순
            @Override
            public int compare(int []a1, int []a2){
                if(a1[0] == a2[0])
                    return a1[1]-a2[1]; //주위!!!
                    /**
                    ex) 4,3 - 1,4 - 1,3 (내림차순으로 할시)
                        (1,3)이 제외된다.(원래는 제외되면 안됨)
                    **/
                return a2[0]-a1[0];
            }
        });
        
        List<Integer> sumList = new ArrayList<>(); //총 점수 관리 배열
        int wonRank = 1; //원호의 실시간 랭킹 순위
        int standSum = won[0] + won[1]; //원호의 점수합(=기준점수)
        int attitudeMaxValue = scores[0][0]; //근무태도 최대값
        int assessmentMaxValue = Integer.MIN_VALUE; //동료 평가 최대값
        for(int[] score: scores){
            //인센티브에 참여하지 못하는 사람인 경우
            if(attitudeMaxValue > score[0] && assessmentMaxValue > score[1]){
                //원호 점수이거나 점수와 같은 경우 -> 결국 원호와 동일하게 생각하면 됌.
                if(won[0]==score[0] && won[1]==score[1])
                    return -1;    
                continue;
            }
            
            //내림차순 정렬이므로 첫번째 근무태도보다는 반드시 같거나 작기 때문에, 동료 평가만 순차적으로 최대값 업데이트
            assessmentMaxValue = Math.max(assessmentMaxValue, score[1]);
            
            //원호보다 높은 점수의 인원인 경우
            if(score[0]+score[1] > standSum){
                 wonRank++; 
                //이미 존재하는 점수인 경우
                if(sumList.contains(score[0]+score[1])){
                    continue;
                }
                sumList.add(score[0]+score[1]);
            }
        }
        return wonRank;
    }
}

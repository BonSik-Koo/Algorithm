import java.util.*;

//이분 탐색 문제
class Solution {
    public long solution(int[] weights) {
        long answer = 0;
        Arrays.sort(weights);
        int len = weights.length;
        int prev = 0;
        
        //완전 탐색 할 경우 -> 시간초과!!
        for(int i=0;i<len-1;i++){
            
            //중복 경우의 수 존재하는 경우 처리!!!!
            //만약 이전의 값과 현재값이 동일하다면, (이전 작업의 경우의 수 - 1) 만큼 동일하게 존재하게 됌.
            if(i > 0 && weights[i] == weights[i-1]){
                prev--;
                answer+=prev;
                continue;
            }
            
            /**
            - 정렬했기 때문에 판별할 경우는 아래와 같음 -> 이분 탐색으로 '*2' 인이유도 최대 가능한 경우가 2배이기 때문.
            a==b
            a*2 == b*1
            a*3 == b*2
            a*4 == b*3
            **/
            prev = 0;
            int j = findRight(weights[i], weights, i+1, len);
            for(; j>i; j--){
                if(weights[i]==weights[j] || weights[i]*2 == weights[j] 
                   || weights[i]*3 == weights[j]*2 || weights[i]*4 == weights[j]*3)
                    prev++;
            }
            answer += prev;
        }
        return answer;
    }
    
    //이분 탐색으로 탐색할 인덱스 찾기
    public int findRight(int w, int[]weigths, int index, int len){
        int left = index;
        int right = len -1;
        int mid = 0;
        
        while(left <= right){
            mid = left + (right-left)/2;
            if(w*2 < weigths[mid])
                right = mid - 1;
            else 
                left = mid +1;
        }
        return mid;
    }
}

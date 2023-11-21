package programmers.kakao.듀큐합같게만들기;
import java.util.*;

class Solution {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -1;
        Queue<Integer> list1 = new LinkedList<>();
        Queue<Integer> list2 = new LinkedList<>();
        long sum1 = 0;
        long sum2 = 0;
        for(int i=0; i<queue1.length; i++){
            list1.add(queue1[i]);
            sum1 += queue1[i];

            list2.add(queue2[i]);
            sum2 += queue2[i];
        }

        // 초기 답이 없는경우
        if((sum1+sum2)%2!=0){
            return answer;
        }

        int count = 0;
        int maxCount = queue1.length * 2;
        int count1 = 0;
        int count2 = 0;
        while(!list1.isEmpty() && !list2.isEmpty()){ // 한곳이 빌 경우, 답이 없는 경우
            if(sum1 > sum2){ // list1에서 꺼내는 경우
                int value = list1.poll();
                list2.add(value);
                sum1 -= value;
                sum2 += value;
                count1++;
            }else if(sum1 < sum2){ // list1에서 꺼내는 경우
                int value = list2.poll();
                list1.add(value);
                sum2 -= value;
                sum1 += value;
                count2++;
            }else{ //같은 경우
                answer = count;
                break;
            }

            // 최대 횟수를 초과한 경우 답이 없는 경우
            if(count1>=maxCount || count2>=maxCount){
                break;
            }

            count++;
        }


        return answer;
    }
}
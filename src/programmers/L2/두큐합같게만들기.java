package programmers.L2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 그리디 풀이!!
 * 다시풀문제!!
 *
 * 중간의 값을 옮길수 없으니 하나씩 처음껄 빼는걸 반복할수 밖에 없음
 * 핵심!: 언제까지 반복할지지
 * 참고 자료 : https://nahwasa.com/entry/%EC%9E%90%EB%B0%94-%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EB%91%90-%ED%81%90-%ED%95%A9-%EA%B0%99%EA%B2%8C-%EB%A7%8C%EB%93%A4%EA%B8%B0-Lv2-Java
 **/
public class 두큐합같게만들기 {

    public int solution(int[] queue1, int[] queue2) {
        int answer = 0;

        Queue<Integer> q1 =new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        long sum1 = 0, sum2 =0;
        int count=0;
        for(int i=0;i<queue1.length;i++) {
            q1.add(queue1[i]); sum1 +=queue1[i];
            q2.add(queue2[i]); sum2 +=queue2[i];
        }

        if((sum1+sum2)%2!=0)
            return -1;

        int c1 =0; int c2=0; int maxCount= queue1.length*2;
        while (true) {

            if(sum1 == sum2){
                answer = count;
                break;
            }
            if(sum1 > sum2) {  //queue1 의 합이 더 큰 경우 -> queue1를 pop
                Integer poll = q1.poll();
                sum1-=poll;
                sum2+=poll;
                q2.add(poll);
                c1++;
            }else {  //queue2 의 합이 더 큰 경우 -> queue2를 pop
                Integer poll = q2.poll();
                sum2-=poll;
                sum1+=poll;
                q1.add(poll);
                c2++;
            }
            count++; //카운트 증가가
            if(c1>=maxCount || c2>=maxCount)
                return -1;
        }
        return answer;
    }

}

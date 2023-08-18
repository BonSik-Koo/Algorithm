package programmers.L1;

import java.util.Arrays;
import java.util.HashSet;

/**
 * greedy 풀이!!
 *
 * solution -> 테스트케이스 10~14 에 대해서 "런타임 에러" 발생!! (정렬하는시간에 발생하는듯..)
 * solution1 -> 정답 풀이
 */
public class 로또의최고순위와최저순위 {

    public static int[] solution(int[] lottos, int[] win_nums) {
        Arrays.sort(lottos);
        Arrays.sort(win_nums);

        int winIndex =0, zero =0, eqCount =0;
       for(int i=0;i<lottos.length;i++) {

           if(lottos[i]==0) {
               zero++;
               continue;
           }
           while(win_nums[winIndex] != lottos[i]) {
               if(win_nums[winIndex] < lottos[i]) {
                   winIndex++;
                   continue;
               }
               break;
           }
           if(win_nums[winIndex] == lottos[i]) {
               eqCount++;
               winIndex++;
               continue;
           }
       }

        int[] answer = new int[2];
        int[]rank = {6,6,5,4,3,2,1};

        answer[1] = rank[eqCount];
       answer[0] = rank[eqCount+zero];
        return answer;
    }

    public static int[] solution1(int[] lottos, int[] win_nums) {

        HashSet<Integer> lotto_num = new HashSet<>();
        HashSet<Integer> win_num = new HashSet<>();

        int zero = 0;
        for(int i=0;i<lottos.length;i++) {
            if(lottos[i] ==0){
                zero++;
            }else {
                lotto_num.add(lottos[i]);
            }
        }

        int eqCount =0;
        for(int i=0;i< win_nums.length;i++) {
            if( lotto_num.contains(win_nums[i]))
                eqCount++;
        }

        int[] answer = new int[2];
        int[]rank = {6,6,5,4,3,2,1};
        answer[1] = rank[eqCount];
        answer[0] = rank[eqCount+zero];
        return answer;
    }

    public static void main (String []args) {

        int[] lottos = {45, 4, 35, 20, 3, 9};
        int[] win_nums =	{20, 9, 3, 45, 4, 35};

        int[] solution = solution1(lottos, win_nums);

        Arrays.stream(solution).forEach(e -> System.out.println(e+ " "));

    }
}

package SWExpertAcademyProblem.이분탐색9차시.기본문제.광고시간정하기패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * - 이분 탐색 문제
 * - 다시 풀 문제
 */
public class 광고시간정하기 {

    public static class time {
        int start; int end; int sum;
        public time(int start, int end,int sum) {
            this.start = start;
            this.end = end;
            this.sum = sum;
        }
    }
    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/이분탐색9차시/res/input4.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //초기화
            List<time> times  = new ArrayList<>();
            int L = Integer.parseInt(bf.readLine());
            int N = Integer.parseInt(bf.readLine());
            int sum =0;
            for(int i=0;i<N;i++){
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                sum += e-s;
                times.add(new time(s,e,sum));
            }

            //로직 시작
            int result = Integer.MIN_VALUE;
            for(int i=0;i<N;i++){
                int s = 0, begin = times.get(i).start, end = begin + L;

                time lastTime = findLastTime(end, N, times);
                s = lastTime.sum - times.get(i).sum + times.get(i).end - times.get(i).start;//!!!!!!!!!!!

                //마지막 "Time.end > end" 인경우는 신경쓰지 않아도 x -> 위에서 이미 마지막 Time 까지의 시간의 합으로 초기값을 잡아서
                if (lastTime.end > end && lastTime.start < end){ //end 시간이 마지막 Time.start & Time.end 에 속한 경우
                    s -= lastTime.end - end;
                }else if (lastTime.end > end){ //마지막 Time.start 보다도 end 가 작은경우 -> 마지막 Time 의 시간을 뺴주기
                    s -= lastTime.end - lastTime.start;
                }

                result = Math.max(result, s);
            }

            //출력
            System.out.println("#"+test_case+" "+result);
        }
    }

    //마지막 end 시간이 속한 Time 을찾는 기능 -> 이분탐색으로!!
    //기존과 조금 다르게
    public static time findLastTime(int end, int N, List<time>times) {
        int min = 0;
        int max = N-1;

        while (min<max){ //!!
            int mid = min + (max-min)/2;

            if(times.get(mid).end >= end) //!!
                max = mid; //!!
            else
                min = mid+1;
        }
        return times.get(max);
    }

}

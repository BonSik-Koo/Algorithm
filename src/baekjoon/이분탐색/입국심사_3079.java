package baekjoon.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 입국심사_3079 {
    static int[] requiredTime;
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        requiredTime = new int[N];
        long maxTime = Long.MIN_VALUE;
        for(int i=0; i<N; i++){
            int time = Integer.parseInt(br.readLine());
            requiredTime[i] = time;
            maxTime = Math.max(maxTime, time);
        }

        long result = binarySearch(0, maxTime * M);
        System.out.println(result);
    }

    public static long binarySearch(long start, long end){
        long result = Long.MAX_VALUE;
        while(start <= end){
            long mid = (start + end) / 2;

            // 검사 받을 수 있는 경우 기준 시간을 줄이기
            if(check(mid)){
                result = Math.min(result, mid);
                end = mid - 1;
            }else{
                start = mid + 1;
            }
        }
        return result;
    }

    public static boolean check(long time){
        // 기준 시간에 소요 시간을 나누면 상담할 수 있는 인원 수가 계산됨.
        long sum = 0;
        for(int i=0; i<N; i++){
            sum += (time/requiredTime[i]);
            // 기준 시간 안에 모두 검사 받을 수 있는 경우
            if(sum >= M){
                return true;
            }
        }
        return false;
    }

}

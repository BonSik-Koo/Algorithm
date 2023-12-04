package baekjoon.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나머지합_10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] cnt = new int[1000]; // M
        long answer = 0;

        // 누적합 + 나머지(수학)
        // arr[a-1] % M == arr[b] % M 면, 구간 (a,b)의 합을 M으로 나누었을 때 0이 된다.
        // 즉, i 지점까지 누적합 나머지 값과 같은 개수가 그 i 지점까지 존재하는 구간 중 M으로 나눴을 때 0 이 되는 개수 이다.
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        for(int i=0; i<N; i++){
            int value = Integer.parseInt(st.nextToken());
            sum += value; // 누적합
            sum %= M; // 누적합을 M으로 나누기

            answer += cnt[sum];
            if(sum == 0) answer++;
            cnt[sum]++;
        }

        System.out.println(answer);
    }
}

package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 리모컨_1107 {
    static boolean[] button;
    static int size;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        size = String.valueOf(N).length();

        button = new boolean[10]; // 0 ~ 9 버튼 존재
        Arrays.fill(button, true);
        if(M!=0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                // 고장 표시
                button[Integer.parseInt(st.nextToken())] = false;
            }
        }

        dfs(N, 0, 0);
        // 현재 채널에서 목표 채널로 + or - 로만 최소로 갈 수 있음.
        result = Math.min(result, Math.abs(N-100));
        System.out.println(result);
    }

    public static void dfs(int goal, int channel, int count){
        for(int i=0; i<10; i++){
            if(!button[i]) continue;
            int newChannel = channel * 10 + i;

            // 첫자리가 0이 될 수 도 있지만, 이어서 탐색이 되어야 하므로 어쩔 수 없음.
            // 최소 횟수를 구할 때, 첫자리가 0이여도 숫자로 변환하는 과정에서 커버할 수 있음.
            // 현재 숫자에서 최소 횟수 구하기
            int cnt = Math.abs(goal - newChannel) + String.valueOf(newChannel).length();
            result = Math.min(cnt, result);

            // 최대 경우의 수가 1,000,000 이므로 모든 경우의 수를 계산해도 가능하다.
            // 목표 자리수가 한자리인데, 6자리까지 경우의 수를 계산하니, 시간 지연이 발생할 수도 있다 이부분을 최적화 시킬 수도 있다.
            // 최대 목표 채널 자리 수 를 초과한 경우
            // 목표 자리 수의 첫자리가 5이므로 목표 자리 수를 초과해서 - 하는 경우는 없다.
            if(count <= 6) {
                dfs(goal, newChannel, count + 1);
            }
        }
    }

}

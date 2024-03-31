package baekjoon.투포인터;

import java.util.*;
import java.io.*;

public class 같이눈사람만들래_20366 {
    static long[] snow;
    static boolean[] visit;
    static long result = Long.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        snow = new long[N];
        visit = new boolean[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            snow[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(snow);

        dfs(0, 0, new int[2]);
        System.out.println(result);
    }

    static void dfs(int index, int count, int[] choice) {
        if(count == 2) {
            toPoint(choice);
            return;
        }

        for(int i=index; i<snow.length; i++) {
            if(visit[i]) continue;

            visit[i] = true;
            choice[count] = i;
            dfs(i+1, count+1, choice);
            choice[count] = -1;
            visit[i] = false;
        }
    }

    static void toPoint(int[] choice) {
        // 첫 시작 인덱스 찾기
        int low = 0, high = snow.length-1;
        while(low<snow.length && (choice[0] == low || choice[1] == low)) {
            low++;
        }
        while(0<high && (choice[1] == high || choice[0] == high)) {
            high--;
        }

        long sum = snow[choice[0]] + snow[choice[1]];
        while(low < high) {
            long cSum = snow[low] + snow[high];

            if(sum < cSum) {
                high--;
                while(0<high && visit[high]) {
                    high--;
                }
            } else if (sum > cSum) {
                low++;
                while(low<snow.length && visit[low]) {
                    low++;
                }
            } else { // 가장 최소값
                result = 0;
                break;
            }

            result = Math.min(result, Math.abs(sum - cSum));
        }

    }

}

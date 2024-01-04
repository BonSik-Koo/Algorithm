package baekjoon.이분탐색;

import java.util.*;
import java.io.*;
public class 도토리숨기기_15732 {
    static int[][] rules; // [0]: start, [1]: end, [2]: gap

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        rules = new int[K][3];
        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            rules[i][0] = Integer.parseInt(st.nextToken());
            rules[i][1] = Integer.parseInt(st.nextToken());
            rules[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = bs(1, N, D);
        System.out.println(answer);
    }

    static int bs(int low, int high, int D){
        int result = Integer.MAX_VALUE;

        while(low <= high){
            int mid = (high+low)/2;

            long count = findCount(mid);
            if(count >= D){
                high = mid - 1;
                result = Math.min(result, mid);
            }else{
                low = mid + 1;
            }
        }
        return result;
    }

    static long findCount(int boxNumber){
        long count = 0; // 기준 박스까지 담을 수 있는 도토리 개수
        for(int[] rule : rules){
            if(rule[0] > boxNumber) {
                continue;
            }

            int endBoxNum = Math.min(rule[1], boxNumber);
            count += (endBoxNum - rule[0])/rule[2] + 1;
        }
        return count;
    }

}

package baekjoon.이분탐색;

import java.util.*;
import java.io.*;

public class 휴게소세우기_1477 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] pos = new int[N+2];
        for(int i=0; i<N; i++) {
            pos[i] = Integer.parseInt(st.nextToken());
        }
        pos[N] = 0;
        pos[N+1] = L;
        Arrays.sort(pos);

        int result = binarySearch(pos, M, L);
        System.out.println(result);
    }

    static int binarySearch(int[] pos, int M, int L) {
        int result = Integer.MAX_VALUE;
        int low = 1;
        int high = L-1;

        while(low <= high) {
            int mid = (low + high) / 2;
            if(isCreate(pos, mid, M)) {
                result = Math.min(result, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
    static boolean isCreate(int[] pos, int value, int M) {
        int count = M;
        for(int i=0; i<pos.length-1; i++) {
            int diff = pos[i+1] - pos[i];
            count -= (diff%value==0) ? (diff/value-1) : (diff/value);

            if(count < 0) {
                return false;
            }
        }
        return true;
    }

}

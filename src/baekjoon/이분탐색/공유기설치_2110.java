package baekjoon.이분탐색;

import java.util.*;
import java.io.*;

public class 공유기설치_2110 {
    static int[] house;
    static int N, C;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        house = new int[N];
        for (int i = 0; i < N; i++) {
            house[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(house);

        binarySearch(1, house[N-1] - house[0]);
        System.out.println(result);
    }

    public static void binarySearch(int low, int high) {
        while (low <= high) {
            int mid = (high + low) / 2;
            if (isSuccess(mid)) {
                low = mid + 1;
                result = Math.max(result, mid);
            } else {
                high = mid - 1;
            }
        }
    }

    public static boolean isSuccess(int dis) {
        // 투포인트로 가능한지 검증
        int i = 0;
        int j = 1;
        int count = 1;
        boolean success = false;
        while (i < N && j < N) {
            int diffDis = house[j] - house[i];

            if(diffDis >= dis){
                i = j;
                count++;
            }
            j++;

            if(count == C){
                success = true;
                break;
            }
        }
        return success;
    }

}

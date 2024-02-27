package baekjoon.이분탐색;

import java.io.*;
import java.util.*;

public class 사냥꾼_8983 {
    static int M, N;
    static long L;
    static int[] point;
    static int[][] animal;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        point = new int[M];
        for(int i=0; i<M; i++) {
            point[i] = Integer.parseInt(st.nextToken());
        }

        animal = new int[N][2];
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            animal[i][0] = Integer.parseInt(st.nextToken());
            animal[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(point); // 이분 탐색을 위해 정렬

        System.out.println(binarySearch());
    }

    static int binarySearch() {
        int cnt = 0;
        for(int i=0; i<N; i++) {
            // 사냥이 가능한 사대 위치를 이분탐색
            int left = 0;
            int right = M-1;
            while(left <= right) {
                int mid = (left + right) / 2;
                long distance = Math.abs(point[mid] - animal[i][0]) + animal[i][1];

                if(distance <= L) {
                    cnt++;
                    break;
                }

                if(animal[i][0] <= point[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }

            }
        }
        return cnt;
    }

}

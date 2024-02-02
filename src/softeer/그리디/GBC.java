package softeer.그리디;

import java.io.*;
import java.util.*;
public class GBC {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N][3];
        int prePoint = 0;
        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            int point = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());

            arr[i][0] = prePoint;
            arr[i][1] = prePoint + point;
            arr[i][2] = speed;

            prePoint = arr[i][1];
        }

        int[] maxPointSpeed = new int[N];
        int toPoint = 0;
        for(int i=0; i<M ;i++) {
            st = new StringTokenizer(br.readLine());
            int fromPoint = Integer.parseInt(st.nextToken()) + toPoint;
            int speed = Integer.parseInt(st.nextToken());

            for(int j=0; j<N; j++) {
                // 해당 구간에 속하지 않는 경우
                if(arr[j][1]<=toPoint){
                    continue;
                }

                // 구간에 속 들어오는 경우
                if(arr[j][0]<=toPoint && fromPoint<=arr[j][1]) {
                    maxPointSpeed[j] = Math.max(maxPointSpeed[j], speed);
                    break;
                }

                // 구간을 넘어가는 경우
                if(arr[j][0]<=toPoint && arr[j][1]<fromPoint) {
                    maxPointSpeed[j] = Math.max(maxPointSpeed[j], speed);
                    toPoint = arr[j][1];
                    continue;
                }
            }

            toPoint = fromPoint;
        }

        int maxDiff = 0;
        for(int i=0; i<N; i++) {
            maxDiff = Math.max(maxDiff, maxPointSpeed[i]-arr[i][2]);
        }

        System.out.println(maxDiff);
    }

}

package baekjoon.누적합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 달력_20207 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] sum = new int[366];

        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            for(int j=S; j<=E; j++) {
                sum[j]+=1;
            }
        }

        int result = 0;
        int width = 0, high = 0;
        for(int i=1; i<=365; i++) {
            if(sum[i] == 0) {
                result += width * high;

                width = 0;
                high = 0;
                continue;
            }

            if(sum[i] >= 1) {
                width++;
                high = Math.max(high, sum[i]);

                if(i==365) {
                    result += width * high;
                }
            }
        }

        System.out.println(result);
    }
}

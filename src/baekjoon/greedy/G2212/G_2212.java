package baekjoon.greedy.G2212;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class G_2212 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine()); //센서 개수
        int k = Integer.parseInt(bf.readLine()); //집중국 개수
        int[] sensors = new int[n]; //센서들 위치
        int[] diffDistance = new int[n-1]; //센서 순서대로 거리 차이
        int result =0;

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0;i<n;i++) {
            sensors[i] = Integer.parseInt(st.nextToken());
        }

        if(n>k) {
            Arrays.sort(sensors);

            for(int i=0;i<n-1;i++)
                diffDistance[i] = sensors[i+1] - sensors[i];
            Arrays.sort(diffDistance);

            for(int i=0;i<(n-1 -(k-1));i++)
                result +=diffDistance[i];
        }

        System.out.println(result);
    }

}

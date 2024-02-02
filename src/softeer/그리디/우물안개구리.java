package softeer.그리디;

import java.io.*;
import java.util.*;

public class 우물안개구리 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N+1];
        int[] maxFriendWeight = new int[N+1];
        boolean[] visit = new boolean[N+1];

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            visit[A] = true;
            visit[B] = true;

            maxFriendWeight[A] = Math.max(maxFriendWeight[A], arr[B]);
            maxFriendWeight[B] = Math.max(maxFriendWeight[B], arr[A]);
        }

        int result = 0;
        for(int i=1; i<=N; i++) {
            if(!visit[i]) {
                result++;
                continue;
            }

            if(arr[i] > maxFriendWeight[i]) {
                result++;
            }
        }
        System.out.println(result);
    }
}

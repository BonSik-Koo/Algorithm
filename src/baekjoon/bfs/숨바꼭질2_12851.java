package baekjoon.bfs;

import java.io.*;
import java.util.*;

public class 숨바꼭질2_12851 {
    static int MAX_VALUE = Integer.MAX_VALUE;
    static int MAX_SIZE = 200000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] visit = new int[MAX_SIZE+1];
        Arrays.fill(visit, MAX_VALUE);

        int minCount = MAX_VALUE;
        int sumCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{N, 0});
        visit[N] = 0;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if(cur[0] == K) {
                minCount = cur[1];
                sumCount++;
                continue;
            }

            // 조기 멈춤 조건
            if(minCount != MAX_VALUE) {
                if(cur[1] >= minCount) {
                    continue;
                }
            }

            int nCur = cur[0] - 1;
            if(nCur>=0 && (visit[nCur]>=cur[1]+1)) {
                visit[nCur] = Math.min(visit[nCur], cur[1]+1);
                queue.add(new int[]{nCur, cur[1]+1});
            }
            nCur = cur[0] + 1;
            if(nCur<=MAX_SIZE && (visit[nCur]>=cur[1]+1)) {
                visit[nCur] = Math.min(visit[nCur], cur[1]+1);
                queue.add(new int[]{nCur, cur[1]+1});
            }

            nCur = cur[0] * 2;
            if(nCur<=MAX_SIZE && (visit[nCur]>=cur[1]+1)) {
                visit[nCur] = Math.min(visit[nCur], cur[1]+1);
                queue.add(new int[]{nCur, cur[1]+1});
            }

        }

        System.out.println(minCount);
        System.out.println(sumCount);

    }
}

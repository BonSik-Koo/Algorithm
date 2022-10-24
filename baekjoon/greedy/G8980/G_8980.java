package baekjoon.greedy.G8980;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * !hard!
 *
 * <고려사항>
 * - !가장 많은 택배를 나르기 위해서는
 *   1) 빠르게 도착하는 마을의 박스 개수를 트럭에 담아야된다.
 *   2) 추가로 마을에 동시에 도착하는 상황에서는 "출발점과 목적지의 길이가 짧은 곳" 즉 출발마을과 목적마을의 길이가 짧은 순대로 트럭에 담아야된다.
 */
public class G_8980 {

    public static class box implements Comparable<box> {
        int start; int end; int count;

        public box(int start, int end, int count) {
            this.start = start;
            this.end = end;
            this.count = count;
        }

        @Override
        public int compareTo(box o) {
            if(this.end == o.end)
                return o.start - this.start;
            return this.end - o.end;
        }
    }
    public static void main(String[] args) throws IOException {

        List<box> list = new ArrayList<>();
        int result = 0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int cityNum = Integer.parseInt(st.nextToken()); //마을 개수
        int maxBoxCount = Integer.parseInt(st.nextToken());

        int []cityBoxMaxCount = new int[cityNum-1];
        Arrays.fill(cityBoxMaxCount, maxBoxCount); //초기값 설정

        int count = Integer.parseInt(bf.readLine());
        for(int i=0;i<count;i++) {
            st = new StringTokenizer(bf.readLine());
            list.add(new box(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1,
                    Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list); //기준에 맞춰서 정렬

        for(int i=0;i<list.size();i++) {
            box box = list.get(i);

            int currentMinBoxCount =cityBoxMaxCount[box.start];
            for(int j=box.start+1; j<=box.end-1;j++)
                currentMinBoxCount = Math.min(currentMinBoxCount,cityBoxMaxCount[j]);

            int minusCount =0;
            if(box.count <= currentMinBoxCount)
                minusCount = box.count;
            else
                minusCount = currentMinBoxCount;

            for (int k=box.start; k<=box.end - 1; k++)
                cityBoxMaxCount[k]-=minusCount;
            result += minusCount;
        }

        System.out.println(result);
    }
}

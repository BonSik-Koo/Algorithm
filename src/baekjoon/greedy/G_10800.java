package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * !hard!
 * - 혼자 푼문제
 */
public class G_10800 {

    static class Ball {
        int color; int size; int index;
        public Ball(int color, int size, int index) {
            this.color = color;
            this.size = size;
            this.index = index; //결과 인덱스
        }
    }
    public static void main(String[]args) throws IOException {
        List<Ball> list = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(bf.readLine());
            list.add(new Ball(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), i));
        }

        Collections.sort(list, new Comparator<Ball>() { //오름차순 정렬(크기 기준)
            @Override
            public int compare(Ball o1, Ball o2) {
                return o1.size-o2.size;
            }
        });

        int sum = 0; //누적 합계
        int []color = new int[N+1];
        int [] plusColor = new int[N+1];
        int []result = new int[N];
        int preColor=0, preSize=0; int sameCount =0;
        for(int i=0;i<list.size();i++) {
            Ball ball = list.get(i);
            if(preSize < ball.size) {
                sameCount =0;
                Arrays.fill(plusColor, 0);
                result[ball.index] = sum - color[ball.color];

            }else {
                sameCount++;
                plusColor[preColor] += ball.size;
                result[ball.index] = sum - color[ball.color] - preSize*sameCount + plusColor[ball.color];
                //1. 이전공의 크기와 같았던 누적카운터 만큼 빼주어야된다.
                //2. 추가로 color배열에 같은 색의 공이 크기가 계속 누적되는데 이걸 빼주고 앞서 "1"에서 빼준거랑 공통부분이 있어서 추가로(pluColor) 배열에서 더해준다.
            }

            color[ball.color] += ball.size;
            sum += ball.size;
            preColor = ball.color;
            preSize = ball.size;
        }

        for(int n : result)
            System.out.println(n);

    }
}

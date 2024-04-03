package baekjoon.greedy;

import java.util.*;
import java.io.*;

public class 컵라면_1781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Homework[] homeworks = new Homework[N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int deadLine = Integer.parseInt(st.nextToken());
            int count = Integer.parseInt(st.nextToken());
            homeworks[i] = new Homework(deadLine, count);
        }

        // 데드라인 오름차순, 컵라면 수 내림차순 정렬
        Arrays.sort(homeworks);

        // 컵라면 수 오름차순
        Queue<Integer> queue = new PriorityQueue<>();
        for(Homework cur : homeworks) {
            // 데드라인에 충족하는 작업
            if(queue.size() < cur.deadline) {
                queue.add(cur.count);
            }
            // 데드라인 외에 최대 컵라면수 찾기
            else {
                if(queue.peek() < cur.count) {
                    queue.poll();
                    queue.add(cur.count);
                }
            }
        }

        // 선택된 작업들
        long result = 0;
        while(!queue.isEmpty()) {
            result += queue.poll();
        }

        System.out.println(result);
    }

    static class Homework implements Comparable<Homework>{
        int deadline, count;
        public Homework(int deadline, int count) {
            this.deadline = deadline;
            this.count = count;
        }

        @Override
        public int compareTo(Homework target) {
            if(deadline == target.deadline) {
                return target.count - count;
            }
            return deadline - target.deadline;
        }
    }
}

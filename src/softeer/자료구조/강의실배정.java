package softeer.자료구조;

import java.io.*;
import java.util.*;
public class 강의실배정 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Time> queue = new PriorityQueue<>();
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue.add(new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Time init = queue.poll();
        int startTime = init.toTime;
        int endTime = init.fromTime;
        int result = 1;
        while(!queue.isEmpty()) {
            Time nextTime = queue.poll();
            // 강의가 가능한 경우
            if(endTime <= nextTime.toTime) {
                result++;
                endTime = nextTime.fromTime;
            }
        }

        System.out.println(result);
    }

    static class Time implements Comparable<Time>{
        int toTime, fromTime;
        public Time(int toTime, int fromTime) {
            this.toTime = toTime;
            this.fromTime = fromTime;
        }
        @Override
        public int compareTo(Time time){
            if(this.fromTime == time.fromTime) {
                return this.toTime - time.toTime;
            }
            return this.fromTime - time.fromTime;
        }
    }
}

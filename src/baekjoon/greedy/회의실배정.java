package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class 회의실배정 {

    static class Time{
        int start, end;
        public Time(int start,int end){
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[]args) throws IOException {
        List<Time> times = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            Time createTime = new Time(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            times.add(createTime);
        }

        //종료 시간 오름차순으로 정렬
        //종료 시간이 짧은것부터 선택해야지 최대한 많은 회의를 선택할 수 있음.
        times.sort(new Comparator<Time>() {
            @Override
            public int compare(Time o1, Time o2) {
                if(o1.end == o2.end){
                    return o1.start - o2.start;
                }
                return o1.end - o2.end;
            }
        });

        //판별 시작
        int endTime = times.get(0).end; //시작 기준점
        int count = 1;
        for(int i=1;i<N;i++){
            Time nextTime = times.get(i);
            if(endTime <= nextTime.start){
                endTime = nextTime.end;
                count++;
            }
        }

        System.out.println(count);
    }
}

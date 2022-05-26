package programmers.L3.bps;

import java.lang.reflect.Array;
import java.util.*;

public class 여행경로 {

    public static boolean[] visit;
    public static String result; //결과의 경로를 담는 변수
    public static int check=0; //오름차순의 경로를 구할경우 나머지 경우를 계산하지 않기 위해 사용

    public static String[] solution(String[][] tickets) {
        String[] answer = {};

        //오름차순으로 정렬
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if(o1[0].equals(o2[0])) {
                    return o1[1].compareTo(o2[1]);
                }
                else
                    return o1[0].compareTo(o2[0]);
            }
        });
        
        visit= new boolean[tickets.length];
        Arrays.fill(visit,false);

        bps(tickets, "ICN","ICN",0);
      
        answer = result.split(" ");
        //System.out.println(Arrays.toString(answer));
        return answer;
    }
    public static void bps(String[][]tickets, String start, String router,int count ) {
      
        if( count == tickets.length) {
            check=tickets.length;   
            result = router;
            return;
        }
        if(check==5)
            return;
      
        for(int i =0; i<tickets.length;i++) {
            if(tickets[i][0].equals(start) && visit[i]!=true) {
                visit[i]=true;
                bps(tickets, tickets[i][1], router + " " + tickets[i][1],count+1);
                visit[i]=false;
            }
        }
    }

    public static void main(String[] args) {

        String[][] temp ={{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        solution(temp);

    }
}


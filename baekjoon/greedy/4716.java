import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <풀이방법>
 * - 풍선을 나르는 거리가 최소가 되기위해서는 각팀별로 A,B로 가는 거리차이가 많이 나는 팀부터 처리한다.
 *   - A,B거리 차이가 많이 나는 경우에 더해지는 총합 거리가 늘어나기 때문이다.
 * - list에 넣고 하나씩 꺼내면서 각 팀에서 A,B로 가는 거리가 짧은거 부터 처리한다.
 */
public class G_4716 {

    static class Team implements Comparable<Team> {
        int K =0;int DA =0, DB=0;
        public Team(int K,int DA, int DB) {
            this.K = K;
            this.DA = DA;
            this.DB = DB;
        }
        @Override
        public int compareTo(Team o) {
            return (Math.abs(o.DA-o.DB)) - (Math.abs(this.DA-this.DB));
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        List<Team> list = new ArrayList<>();

        while(true) {
            st = new StringTokenizer(bf.readLine());
            int result =0;
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            if(N==0 && A==0 && B==0)
                break;
            for(int i=0;i<N;i++) {
                st = new StringTokenizer(bf.readLine());
                list.add(new Team(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
            }
            Collections.sort(list);//정렬
            for(int i=0;i<N;i++) { //실제 로직
               Team team = list.get(i);
                if(team.DA>=team.DB) { //B의 풍선을 주는 경우
                    if(B-team.K < 0) { //B의 풍선의 개수를 초과하는 경우
                        result+=B*team.DB;
                        result+=(team.K-B)*team.DA;
                        A-=team.K-B;
                        B=0;
                    }else{ //B의 풍선으로 다 줄수 있는 경우
                        result +=team.K * team.DB;
                        B -=team.K;
                    }
                }else {//A의 풍선을 주는 경우
                    if(A-team.K <0){
                        result+=A*team.DA;
                        result+=(team.K-A)*team.DB;
                        B-=team.K-A;
                        A=0;
                    }else {
                        result +=team.K * team.DA;
                        A-=team.K;
                    }
                }
            }
            System.out.println(result);
            list.clear(); //초기화
        }
    }
}

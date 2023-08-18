import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 가격순으로 오름차순 정렬하되 최대로 되려면 마감일에 강의를 진행하는것이 베스트!
 */
public class G_2109 {

    static class University implements Comparable<University>{
        int p; int d;
        public University(int p, int d) {
            this.p = p;
            this.d = d;
        }
        @Override
        public int compareTo(University o) {
            if(o.p == this.p)
                return this.d - o.d;
            return o.p - this.p;
        }
    }
    public static void main(String[] args) throws IOException {
        Queue<University> queue = new PriorityQueue<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(bf.readLine());

        int maxDay =0;
        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            maxDay = Math.max(maxDay, d);
            queue.add(new University(p,d));
        }

        Boolean[] check = new Boolean[maxDay+1];
        Arrays.fill(check, false);
        int result = 0;
        while(!queue.isEmpty()) {
            University poll = queue.poll();

            for(int i = poll.d; i>=1;i--){
                if(check[i]!=true) {//공연을 할수있는날
                    result += poll.p;
                    check[i] = true;
                    break;
                }
            }
        }
        System.out.println(result);
    }
}

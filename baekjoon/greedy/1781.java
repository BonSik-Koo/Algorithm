import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
/**
 * <풀이법>
 * - 데드라인순으로 오름차순 정렬
 * - 같은 데드라인이 가진 문제는 컴라면순으로 내림차순 정렬
 * - 정렬된 배열에서 순서대로 진행하면서 현재 시간과 문제의 데드라인을 맞출수 있는지 판별하고 되면 진행 안되면 버리기
 */
public class G_1781 {

    static class Problem implements Comparable<Problem> {
        int deadline; int result;
        public Problem(int deadline, int result) {
            this.deadline = deadline;
            this.result = result;
        }
        @Override
        public int compareTo(Problem o) {
            if(this.result > o.result)
                return -1;
            else if(this.result < o.result)
                return 1;
            else {
                if(this.deadline > o.deadline)
                    return 1;
                else
                    return -1;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        List<Problem> list = new ArrayList<>();
        int result=0 , time=0, maxDedeLine=0;
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        for(int i=0;i<num;i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            list.add(new Problem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }

        Collections.sort(list);

        for(int i=0;i<list.size();i++) {
            Problem problem = list.get(i);
            if(problem.deadline>time) {
                time++;
                result += problem.result;
                maxDedeLine = Math.max(maxDedeLine, problem.deadline);
            }else {
                if (maxDedeLine - 1 >= time) {
                    time++;
                    result += problem.result;
                    maxDedeLine = Math.max(maxDedeLine, problem.deadline);
                }
            }
        }
        System.out.println(result);
    }
}


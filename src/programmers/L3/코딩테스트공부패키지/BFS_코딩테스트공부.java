package programmers.L3.코딩테스트공부패키지;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * BFS 문제 풀이 -> 반드시 시간초과!!!!!!!!!
 */
public class BFS_코딩테스트공부 {

    public static int solution(int alp, int cop, int[][] problems) {
        int answer = Integer.MAX_VALUE;
        class Pos {
            int alp; int cop; int t;
            public Pos(int alp, int cop, int t) {
                this.alp = alp;
                this.cop = cop;
                this.t = t;
            }
        }
        PriorityQueue<Pos> queue = new PriorityQueue<>(new Comparator<Pos>() {
            @Override
            public int compare(Pos o1, Pos o2) {
                return o1.t - o2.t;
            }
        });
        int alpMax=0, colMax=0;
        int nSize = problems.length+2;
        int[][] nProblems = new int[nSize][5];
        for(int i=0;i<problems.length;i++) {
            alpMax = Math.max(alpMax, problems[i][0]);
            colMax = Math.max(colMax, problems[i][1]);
            nProblems[i][0] = problems[i][0]; nProblems[i][1] = problems[i][1];
            nProblems[i][2] = problems[i][2]; nProblems[i][3] = problems[i][3];
            nProblems[i][4] = problems[i][4];
        }
        nProblems[nSize-2][0]=0; nProblems[nSize-2][1]=0; nProblems[nSize-2][2]=1; nProblems[nSize-2][3]=0; nProblems[nSize-2][4]=1;
        nProblems[nSize-1][0]=0; nProblems[nSize-1][1]=0; nProblems[nSize-1][2]=0; nProblems[nSize-1][3]=1; nProblems[nSize-1][4]=1;

        Boolean [][]check = new Boolean[160][160];
        for(int i=0;i<check.length;i++)
            Arrays.fill(check[i],false);
        queue.add(new Pos(alp, cop,0));
        while (!queue.isEmpty()) {
            Pos pos = queue.poll();

            if(pos.alp>=alpMax && pos.cop>=colMax){
                answer = pos.t;
                break;
            }
            for(int i=0;i<nSize;i++) {
                if(pos.alp>=nProblems[i][0] && pos.cop>=nProblems[i][1]) { //풀수있을때
                    if(check[pos.alp+nProblems[i][2]][pos.cop+nProblems[i][3]]!=true) {
                        queue.add(new Pos(pos.alp + nProblems[i][2], pos.cop + nProblems[i][3], pos.t + nProblems[i][4]));
                        check[pos.alp][pos.cop]=true;
                    }
                }
            }
        }
        return answer;
    }


    public static void main(String[] args) {

        int alp = 0; int cop = 0;
        int[][] problems = {{0,0,2,1,2},{4,5,3,1,2},{4,11,4,0,2},{10,4,0,4,2}};
        int solution = solution(alp, cop, problems);
        System.out.println(solution);
    }

}

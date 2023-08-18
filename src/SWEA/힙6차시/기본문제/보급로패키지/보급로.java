package SWEA.힙6차시.기본문제.보급로패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * - BFS 문제
 * - 힙 - 우선순위 큐 사용 문제
 */
public class 보급로 {

    public static class loc implements Comparable<loc> {
        int x ; int y; int time;
        public loc(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
        @Override
        public int compareTo(loc o) {
            return this.time - o.time;
        }
    }
    public static int[][] field;
    public static int[][] times; //좌표의 시간최소값을 담고있는 배열
    public static Boolean[][] visit;
    public static int[]dx = {1,-1,0,0};
    public static int[]dy = {0,0,1,-1};

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/힙6차시/res/input3.txt"));

        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //초기화
            int N = Integer.parseInt(bf.readLine());
            field = new int[N][N]; times = new int[N][N]; visit = new Boolean[N][N];
            for(int i=0;i<N;i++){
                String str = bf.readLine();
                for(int j=0;j<str.length();j++){
                    field[i][j] = Character.getNumericValue(str.charAt(j));
                    visit[i][j] = false;
                    times[i][j] = Integer.MAX_VALUE;
                }
            }

            int result = bfs(N);
            System.out.println("#"+test_case+" "+result);
        }
    }
    public static int  bfs(int N) {

        int result =0;
        PriorityQueue<loc> queue = new PriorityQueue<>(); //낮은 시간일수록 우선순위가 높다.
        queue.add(new loc(0,0,0));
        visit[0][0] = true; times[0][0] = 0;
        while (!queue.isEmpty()){
            loc poll = queue.poll();
            if(poll.x==N-1 && poll.y==N-1){
                result = poll.time;
                break;
            }

            for(int i=0;i<4;i++){
                int nx = poll.x + dx[i]; int ny = poll.y+dy[i];
                if(nx<0 || nx>=N || ny<0 || ny>=N)
                    continue;

                int nt = poll.time + field[nx][ny];
                if(!visit[nx][ny]) { //아직 아무도 방문하지 않은 좌표
                    queue.add(new loc(nx, ny, nt));
                    visit[nx][ny] = true; //방문처리
                    times[nx][ny] = nt;
                }else { //누군가 방문한 좌표
                    if(nt < times[nx][ny]){ //기존의 있던 시간보다 더 작은 경우가 있는 경우
                        queue.add(new loc(nx, ny, nt));
                        times[nx][ny] = nt;
                    }
                }
            }
        }
        return result;
    }
}

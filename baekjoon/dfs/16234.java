import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <풀이순서></풀이순서>
 * 1. while문으로 인접한 국가의 인구차이가 조건에 만족할때까지 반복
 * 2. dfs 통해서 국경을 공유할수 있는 나라들을 찾기 (있을시 -> 나라수와 인구합 추가, list 에 담기, 방문 처리)
 * 3. 국경을 공유하는 나라가 2개 이상 있을시 인구 이동 -> 인구수 업데이트
 * 4. 모든 나라를 한번씩 다 방문 완료했으면 다시 1번으로 가서 반복
 */
public class DFS_16234 {

    static int[][]map;
    static boolean[][]visit;
    static int min =0; //이상 기준
    static int max = 0; //이하 기준
    static int num = 0; //행렬 크기
    static int[]x ={1,-1,0,0};
    static int[]y = {0,0,1,-1};

    static class loc{
        int x; int y;
        public loc(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static List<loc> list = new ArrayList<>();
    static int currentCount =0;
    static int currentSum =0;

    public static void dfs(int row, int col) {
        int newX=0; int newY =0;

        for(int i=0;i<4;i++) {
            newX = row + x[i];
            newY = col + y[i];

            if((0<=newX && newX<num) && (0<=newY && newY<num) && visit[newX][newY]!=true) { //유망성 검사
                int diff = Math.abs(map[row][col] - map[newX][newY]);

                if(min<=diff && diff<=max) { //국경 공유가 되는 경우
                    currentCount++; //국경을 공유하는 나라 개수
                    currentSum +=map[newX][newY]; //인구수 합
                    list.add(new loc(newX, newY)); //국경을 공유하는 나라 list
                    visit[newX][newY]=true;

                    dfs(newX, newY);
                }
            }
        }

    }


    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int count =0;
        int result=0;

        num = Integer.parseInt(st.nextToken());
        min = Integer.parseInt(st.nextToken());
        max = Integer.parseInt(st.nextToken());

        map = new int[num][num];
        visit = new boolean[num][num];

        for(int i=0;i<num;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<num;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            for(int i=0;i<num;i++) {
                for(int j=0;j<num;j++) {
                    if(visit[i][j] !=true) { //방문하지 않은 나라
                        visit[i][j] = true;
                        currentCount++;
                        currentSum+=map[i][j];
                        list.add(new loc(i,j));

                        dfs(i,j);

                        if(currentCount>=2) { //국경을 공유하는 나라가 2개 이상 -> 인구 이동 가능
                            int avg = currentSum/currentCount; //인구 이동 평균 구하기

                            for(int k=0;k<list.size();k++) {
                                map[list.get(k).x][list.get(k).y] = avg;
                            }
                        }else
                            count++;

                        //초기화
                        list.clear();
                        currentCount =0;
                        currentSum =0;
                    }
                }
            }

            //국경을 공유하는 나라가 아무도 없음 -> 종료
            if(count == num*num)
                break;

            //초기화
            for(int i =0;i<num;i++) {
                for(int j=0;j<num;j++) {
                    visit[i][j] = false;
                }
            }
            count =0;
            result++; //경과 일수 증가
        }

        System.out.println(result);
    }
}

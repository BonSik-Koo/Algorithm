package baekjoon.greedy.G3109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <풀이법>
 * 1) 각 행을 시작으로 dfs 로 경로를 찾는다.
 *    1.1) 경로를 찾을때 0행에서 ~ n행으로 가면서 dfs를 시작한다.
 *    1.2) 최대 경로를 찾아야되기 때문에 "오른쪽대각선위"-"오른쪽"-"오른쪽대각선아래"로 가면서 dfs를 시작한다.
 *         -> 그 이유는 "0~n"행으로 순차적으로 찾기 때문에 최대한 위로 이동하면서 경로를 찾아야지 아래 행들이 가질수 있는 경로가 많아진다.
 *    1.3) 만약 한 행이 가장 끝열에 도착했다면(경로가 형성되었다) 경로수를 늘리고 "백트래킹"을 방지하기 위해 "return"을 통해 그 다음 행이 시작할수 있도록 한다.
 */
public class G_3109 {
    static int row, col;
    static char[][]field;
    static boolean[][] visit;
    static int result=0;
    static int[]x = {-1,0,1}; //대각선위,오른쪽, 대각선 아래 -> 순서가 중요 위에서부터 아래로 하나씩 행이 시작하니 최대한 위로 붙어서 경로를 형성해야지 밑에 있는 행의 경로가 최대로 만들어진다!!!!
    static int[]y = {1,1,1};

    public static boolean dfs(int ro, int co) {

        if(co==col-1) { //종료 조건
            result++;
            return true;
        }

        for(int j=0;j<3;j++) { //(오른쪽, 대각선위, 대각선) 아래 총 3개
            int newRow = ro+x[j];
            int newCol = co+y[j];
            //유망성 판별
            if((0>newRow || row<=newRow) || (0>newCol || col<=newCol))
                continue;
            if(field[newRow][newCol]=='.' && visit[newRow][newCol]!=true) {
                visit[newRow][newCol]=true; //방문처리

                if(dfs(newRow, newCol)) //한 행에서 시작한 것이 끝에 도달했을때(경로가 형성되었을때)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        field = new char[row][col];
        visit = new boolean[row][col];

        for(int i=0;i<row;i++) {
            st = new StringTokenizer(bf.readLine());
            char[] chars = st.nextToken().toCharArray();
            field[i] = chars;
        }

        for(int i=0;i<row;i++)
            dfs(i, 0);

        System.out.println(result);
    }
}

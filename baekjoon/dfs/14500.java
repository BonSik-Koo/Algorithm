import java.util.Scanner;

public class DFS_14500 {
    static int row =0;
    static int col = 0;
    static int[][]map;
    static boolean[][] visit;
    static int result = Integer.MIN_VALUE;

    static int[]x = {1,-1,0,0};
    static int[]y= {0,0,1,-1};

    public static void dfs(int ro, int co, int count, int sum) {
        if(count==4) {
            result = Math.max(result, sum);
            return;
        }

        for(int i=0;i<4;i++) {
            int nx = ro + x[i];
            int ny = co + y[i];

            if((0<=nx && nx<row) && (0<=ny && ny<col) && visit[nx][ny]!=true) {
                visit[nx][ny] = true;
                dfs(nx, ny, count+1, sum+map[nx][ny]);
                visit[nx][ny] = false;
            }
        }

    }
    public static void exception(int ro, int co) { //ㅏ,ㅗ,ㅓ,ㅜ

        if((ro!=row-1) && ((col-1-co)>=2)) { //ㅜ
            result = Math.max(result,map[ro][co]+map[ro][co+1]+map[ro][co+2]+map[ro+1][co+1]);
        }
        if( (ro!=0) && ((col-1-co)>=2)) { //ㅗ
            result = Math.max(result,map[ro][co]+map[ro][co+1]+map[ro][co+2]+map[ro-1][co+1]);
        }
        if( (co!=col-1) && ((row-1-ro)>=2)) { //ㅏ
            result = Math.max(result,map[ro][co]+map[ro+1][co]+map[ro+2][co]+map[ro+1][co+1]);
        }
        if( (co!=0) && ((row-1-ro)>=2)) { //ㅓ
            result = Math.max(result,map[ro][co]+map[ro+1][co]+map[ro+2][co]+map[ro+1][co-1]);
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        row = sc.nextInt();
        col = sc.nextInt();
        map = new int[row][col];
        visit = new boolean[row][col];

        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {
                visit[i][j] = true;
                dfs(i,j,1,map[i][j]);
                visit[i][j] = false;
                exception(i,j);
            }
        }
        System.out.println(result);
    }
}

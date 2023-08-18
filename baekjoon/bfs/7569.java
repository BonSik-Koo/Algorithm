import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bfs_7569 {
    static int [][][]board;
    static int result =0;
    static int x, y ,z ; //초기 보드크기
    static int[] loc_x = {1,-1,0,0,0,0};
    static int[] loc_y = {0,0,1,-1,0,0};
    static int[] loc_z = {0,0,0,0,1,-1};
    static int tomatoNumCheck=0; //익은 토마토 수 체크!!!

    public static class location {
        int x , y, z ; //열,행,높이
        int day;
        public location(int x1, int y1, int z1, int day) {
            this.x = x1; this.y=y1; this.z=z1; this.day=day;
        }
    }

    public static void bfs(Queue<location> queue) {
        boolean check = false;
        int n_day=0;

        while(!queue.isEmpty()) {
            location poll = queue.poll();

            for(int i=0;i<6;i++) {
                int n_x = poll.x + loc_x[i];
                int n_y =poll.y + loc_y[i];
                int n_z = poll.z + loc_z[i];
                n_day = poll.day+1;

                if((0<=n_x && n_x<x) && (0<=n_y && n_y<y) && (0<=n_z && n_z<z)){
                    if(board[n_z][n_y][n_x]==0) {
                        queue.add(new location(n_x, n_y, n_z, n_day));
                        board[n_z][n_y][n_x]=1;
                        tomatoNumCheck--;
                        result = n_day;
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        Queue<location> queue = new LinkedList<location>();

        Scanner scanner = new Scanner(System.in);
        x = scanner.nextInt();
        y = scanner.nextInt();
        z= scanner.nextInt();

        board = new int[z][y][x];
        for(int i=0;i<z;i++) {
            for(int j=0;j<y;j++) {
                for(int k=0;k<x;k++){
                    int temp = scanner.nextInt();
                    board[i][j][k] = temp;
                    if(temp==1) {
                        location loc = new location(k, j, i,0);
                        queue.add(loc);
                    }
                    else if(temp==0)
                        tomatoNumCheck++;
                }
            }
        }
        bfs(queue);
        System.out.println(tomatoNumCheck ==0 ? result: -1);
    }
}

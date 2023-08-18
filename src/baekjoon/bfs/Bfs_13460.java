package baekjoon.bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bfs_13460 {

    static char [][]map;
    static int[] x = {1,-1,0,0};
    static int[] y = {0,0,1,-1};
    static int result=Integer.MAX_VALUE;
    //static boolean[][] visitRed;

    static class beads {
        int rx, ry;
        int bx, by;
        int resultNum;
        boolean[][] visitRed;
        boolean[][] visitBlue;
        int previous;

        public beads(int Rx,int Ry, int Bx, int By, int rNum, boolean[][]visitRed,boolean[][] visitBlue, int previous) {
            this.rx = Rx; this.ry=Ry;
            this.bx =Bx; this.by=By;
            this.resultNum = rNum;
            this.visitRed = visitRed;
            this.visitBlue=visitBlue;
            this.previous = previous;
        }
    }

    public static void bfs(Queue<beads> queue) {
        int RMoveNum=0; int BMoveNum = 0;
        boolean R_move = false;
        boolean B_move = false;

        while(!queue.isEmpty()) {
            beads poll = queue.poll();
            for(int index=0;index<4;index++) {

                int rx = poll.rx; int ry=poll.ry;
                int bx=poll.bx; int by =poll.by;
                int resultNum =poll.resultNum;

                boolean[][] visitRed = new boolean[poll.visitRed.length][poll.visitRed[0].length];
                for(int i =0;i<poll.visitRed.length;i++) {
                    for(int j=0;j<poll.visitRed[i].length;j++)
                        visitRed[i][j] = poll.visitRed[i][j];
                }
                //boolean[][] visitRed = poll.visitRed;
                boolean[][] visitBlue = new boolean[poll.visitBlue.length][poll.visitBlue[0].length];
                for(int i =0;i<poll.visitBlue.length;i++) {
                    for(int j=0;j<poll.visitBlue[i].length;j++)
                        visitBlue[i][j] = poll.visitBlue[i][j];
                }


                int pre = poll.previous;

                //빨간구슬이동할수 있을때 직선 끝까지 이동
                while (map[rx + x[index]][ry + y[index]] != '#' && visitRed[rx + x[index]][ry + y[index]] != true) {
                    if (map[rx + x[index]][ry + y[index]] == 'O')
                        break;
                    visitRed[rx + x[index]][ry + y[index]] = true;
                    rx = rx + x[index];
                    ry = ry + y[index];
                    RMoveNum++;
                    R_move = true; //R공이 이동했다는 의미
                }
                //System.out.println("RMove: " + RMoveNum);

                //파란구슬 이동할수 있을때 직선 끝까지 이동
                while (map[bx + x[index]][by + y[index]] != '#' && visitBlue[bx +x[index]][by+y[index]]!=true) {
                    //System.out.println("inputinputinput");
                    if (map[bx + x[index]][by + y[index]] == 'O')
                        break;
                    visitBlue[bx+x[index]][by+y[index]] =true;
                    bx = bx + x[index];
                    by = by + y[index];
                    BMoveNum++;
                    B_move = true;
                }
                //System.out.println("BMove: " + BMoveNum);

                if (map[rx + x[index]][ry + y[index]] == 'O') { //R공이 구멍에 들어갔을 경우
                    System.out.println("holeholehole");
                    System.out.println("indexindex:" + index);
                    System.out.println("resultNumtemptemp:" + resultNum);
                    System.out.println(index);
                    System.out.println("R:" + rx + " " + ry);
                    System.out.println("B:" + bx+ " " + by);
                    System.out.println("result:" + result);
                    if (map[bx + x[index]][by + y[index]] == 'O') { //B공이 구멍에 들어갔을 경우
                        //System.out.println("inputinputinput");
                        result = -1;
                        return;
                    }
                    else {
                        if (result > resultNum + 1) {
                            result = resultNum + 1;
                        }
                        R_move = false;
                        B_move = false;
                        RMoveNum = 0;
                        BMoveNum = 0;
                        continue;
                    }
                }

                //두공이 겹쳐질 경우
                if (rx == bx && ry == by ) {
                    //System.out.println(RMoveNum + "-" + BMoveNum);
                    if (RMoveNum > BMoveNum) { //레드가 더 많이 이동했다 -> 더 뒤에 있었다
                        visitRed[rx][ry] = false;
                        rx = rx - x[index];
                        ry = ry - y[index];
                    } else {
                        visitBlue[bx][by] = false;
                        bx = bx - x[index];
                        by = by - y[index];
                    }
                }

                if ((R_move == true  || B_move==true) && pre!=index) {

//                    System.out.println(index);
//                    System.out.println("R:" + rx + " " + ry);
//                    System.out.println("B:" + bx+ " " + by);
                    //System.out.println("resultNum:" + resultNum);
                   // System.out.println("result:" + result);
                    //System.out.println();


                    resultNum++;
                    //System.out.println("resultNum:" + resultNum);
                    //System.out.println();
                    pre = index;
                    System.out.println("inputinputinput");
                    System.out.println("rx:" + rx + " ry:"+ ry+" bx:"+bx + " by:" +by+" resultNUm:"+ resultNum);
                    System.out.println();
                    queue.add(new beads(rx, ry, bx, by, resultNum, visitRed,visitBlue, pre));
                    R_move = false;
                    B_move = false;
                    RMoveNum = 0;
                    BMoveNum = 0;
                }
                else{
                    RMoveNum = 0;
                    BMoveNum = 0;
                    R_move = false;
                    B_move = false;
                }
            }

        }
    }

    public static void main(String[] args) {
        Queue<beads> queue = new LinkedList<beads>();
        int x=0, y=0;
        Scanner scanner = new Scanner(System.in);

        String[] s1 = scanner.nextLine().split(" ");
        x= Integer.parseInt(s1[0]); y = Integer.parseInt(s1[1]);
        map = new char[x][y];

        int Rx=0; int Ry=0; int Bx=0; int By=0;
        int holeX=0; int holeY=0;
        for(int i=0;i<x;i++) {
            String s = scanner.nextLine();
            for(int j =0;j<y;j++){
                if(s.charAt(j) == 'R'){
                    Rx=i; Ry=j;
                    map[i][j] = '.';
                }
                else if(s.charAt(j) == 'B'){
                    Bx=i;By=j;
                    map[i][j]='.';
                }
                else
                    map[i][j] = s.charAt(j);
            }
        }

        queue.add(new beads(Rx, Ry, Bx,By,0, new boolean[x][y], new boolean[x][y], 5));
        bfs(queue);

        if(result>=10) {
            System.out.println(-1);
            System.out.println(result);
        }
        else
            System.out.println(result);
    }
}

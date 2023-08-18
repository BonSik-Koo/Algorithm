package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <풀이 순서>
 * 1. cctv를 list에 담는다.
 * 2. dfs로 cctv 종류에 따른 감시할수 있는 영역을 (1,2,3,4)로 경우의 수를 구한다. -> 감시방향이 정해진 cctv를 새로운 list에 담는다.
 * 3. 위에서 담은 list의 cctv를 하나씩 꺼내어 감시할수 있는 방향을 "-1"로 표시한다.
 * 4. 안전지대 영역을 count 한다.
 */
public class DFS_15683 {

    static int row;
    static int col;
    static int[][] map;
    static List<loc> cctv = new ArrayList<>();
    static List<loc> cctvList = new ArrayList<>(); //방향을 포함한 cctv 를 담는 queue
    static int result = Integer.MAX_VALUE;
    static int[] x = {0,0,1,0,-1};
    static int[] y = {0, 1,0,-1,0};

    static class loc {
        int number;
        int row;
        int col;
        List<Integer> loc = new ArrayList<>(); //이동 방향(1,2,3,4)

        public loc(int number, int row, int col) {
            this.number =number;
            this.row = row;
            this.col = col;
        }
        public loc(int number, int row, int col, List<Integer> loc) {
            this.number =number;
            this.row = row;
            this.col = col;
            this.loc = loc;
        }
    }

    public static void dfs(int index) {

        //여기서 사각지대 개수 판별
        if(index ==cctv.size()) {
            move();
            return;
        }

        loc loc = cctv.get(index); //현재 cctv 추출 인덱스
        for(int i=1;i<5;i++) { //이동 방향
            loc loc1  = cctvAdd(loc, i);
            if(loc1 ==null)
                return;

            dfs(index+1);
            cctvList.remove(loc1);
        }
    }

    public static loc cctvAdd(loc loc, int cur) {

        List<Integer> dir = new ArrayList<>();

        if(loc.number ==1)
            dir.add(cur);
        else if (loc.number ==2) {
            if(cur == 1) {
                dir.add(cur);
                dir.add(3);
            }
            else if(cur ==2) {
                dir.add(cur);
                dir.add(4);
            }
            else
                return null;
        }
        else if(loc.number ==3){
            if(cur == 4) {
                dir.add(cur);
                dir.add(1);
            }
            else {
                dir.add(cur);
                dir.add(cur+1);
            }
        }
        else if(loc.number ==4) {
            if(cur == 3 ) {
                dir.add(cur);
                dir.add(cur+1);
                dir.add(1);
            }
            else if(cur == 4) {
                dir.add(cur);
                dir.add(1);
                dir.add(2);
            }
            else {
                dir.add(cur);
                dir.add(cur+1);
                dir.add(cur+2);
            }
        }
        else { //5번
            if(cur == 1) {
                dir.add(1);
                dir.add(2);
                dir.add(3);
                dir.add(4);
            }
            else
                return null;
        }

        loc loc1 = new loc(loc.number, loc.row, loc.col, dir);
        cctvList.add(loc1);
        return loc1;
    }


    public static void move() {
        //copy
        int [][]fillMap = new int[row][col];
        for(int q=0;q<row;q++) {
            for(int w=0;w<col;w++) {
                fillMap[q][w] = map[q][w];
            }
        }

        for(int i=0;i<cctvList.size();i++){
            loc loc = cctvList.get(i);


            for(int j=0;j<loc.loc.size();j++) {
                Integer dir = loc.loc.get(j);
                int newRow= loc.row + x[dir];
                int newCol = loc.col + y[dir];


                while(true) {
                    if((0<=newRow && newRow<row) && (0<=newCol && newCol<col)) {
                        if (fillMap[newRow][newCol] == 6)
                            break;
                        else if (fillMap[newRow][newCol] == 0) {
                            fillMap[newRow][newCol] = -1; //감시 가능 표시
                        }
                    }
                    else
                        break;

                    newRow= newRow + x[dir];
                    newCol = newCol + y[dir];
                }
            }

        }

        int count =0;
        for(int z=0;z<row;z++) {
            for (int t=0;t<col;t++) {
                if(fillMap[z][t] == 0)
                    count++;
            }
        }

        result = Math.min(result, count);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st  = new StringTokenizer(bf.readLine());
        int temp =0;

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];
        for(int i=0;i<row;i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j=0;j<col;j++) {
                temp = Integer.parseInt(st.nextToken());
                if(1<=temp && temp<=5)
                    cctv.add(new loc(temp, i,j));

                map[i][j] = temp;
            }
        }

        dfs(0);
        System.out.println(result);

    }
}

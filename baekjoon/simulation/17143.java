import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class S_17143 {

    static int row =0;
    static int col = 0;
    static int sharkNum =0;
    static int result =0;
    static class shark {
        int row; int col;
        int spreed; int dir;
        int size;
        public shark(int row, int col, int spreed, int dir, int size) {
            this.row = row;
            this.col = col;
            this.spreed = spreed;
            this.dir = dir;
            this.size = size;
        }
    }

    static shark[][] map;
    static List<shark> sharkList = new ArrayList<>();
    static List<Boolean> sharkMoveFinishCheck = new ArrayList<>();
    static int[] x = { -1,0,1,0};
    static int[] y = { 0,-1,0,1};

    //상어 이동
    public static void move() {
        int newRow =0; int newCol =0; int newDir =0;

        for(int i=0;i<sharkList.size();i++) {
            shark shark = sharkList.get(i);
            newRow = shark.row;
            newCol = shark.col;
            newDir = shark.dir;

            for(int j=0;j<shark.spreed;j++) {

                if( 0>(newRow + x[newDir]) || (newRow + x[newDir])==row )
                    newDir = (newDir+2)%4; //방향 전환
                else if( 0>(newCol + y[newDir]) || (newCol + y[newDir])==col )
                    newDir = (newDir+2)%4; //방향 전환

                newRow = newRow + x[newDir];
                newCol = newCol + y[newDir];
            }

            // 상어 한마리 이동 완료 -> 변동된값 업데이트
            shark.row = newRow;
            shark.col = newCol;
            shark.dir = newDir;

        }

        //모든 상어이동후 map 에 업데이트
        shark[][] newMap = new shark[row][col];
        List<shark> deleteList = new ArrayList<>();
        for(int i=0;i<sharkList.size();i++) {
            shark shark = sharkList.get(i);

            if(newMap[shark.row][shark.col] !=null) { //다른 상어가 존재한다는 의미
                shark basicShark = newMap[shark.row][shark.col];
                if(shark.size > basicShark.size) {
                    newMap[shark.row][shark.col] = shark;
                    deleteList.add(basicShark);
                }
                else {
                    deleteList.add(shark);
                }
            }
            else //다른 상어가 존재하지 않음
                newMap[shark.row][shark.col] = shark;
        }

        for(int i=0;i<deleteList.size();i++) {
            sharkList.remove(deleteList.get(i));
        }

        map = newMap;
    }

    public static void catchShark(int col) {

        for(int i=0;i<row;i++){
            if(map[i][col] != null) { //상어가 있다는 의미
                shark shark = map[i][col];
                result+=shark.size;

                map[i][col] = null;
                sharkList.remove(shark);

                break;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        sharkNum = Integer.parseInt(st.nextToken());
        map = new shark[row][col];

        //초기 상어 위치 입력받음
        for(int i=0;i<sharkNum;i++) {
            st = new StringTokenizer(bf.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken()) -1;
            int sp = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int si = Integer.parseInt(st.nextToken());

            if (d == 1) //상
                d= 0;
            else if(d==2) //하
                d= 2;
            else if(d==3) //우
                d=3;
            else //좌
                d=1;

            sharkList.add(new shark(r,c,sp,d,si));
            sharkMoveFinishCheck.add(false); //해당 상어가 아직 이동을 안했다는 의미
        }

        //초기 상어 위치 초기화 -> 초기에는 상어가 겹칠수 없음
        for(int i=0;i<sharkList.size();i++) {

            shark shark = sharkList.get(i);
            map[shark.row][shark.col] = shark;
        }

        //상어 낚시 시작
        for(int i=0;i<col;i++) {

            catchShark(i);
            move();
        }

        System.out.println(result);
    }
}

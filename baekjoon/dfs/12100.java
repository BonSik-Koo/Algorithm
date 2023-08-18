import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * -풀이 순서-
 * 1. dfs로 모든 경우에 대해서 계산
 * 2. 위,아래,오른쪽,왼쪽 의 경우를 나누어 필드값들 계산하기
 * 3. count가 5가 되었을때 끝나는 경우이므로 필드값중 최대값 구하기
 * - dfs 시 깊은 복사를 통해서 원래의 배열로 돌아갈수 있어야된다.
 *
 * 참고) https://jellyinghead.tistory.com/53
 */
public class DFS_12100 {

    static short num; //판의 크기
    static int result = Integer.MIN_VALUE;
    static int[][] field;

    public static void dfs(int count) {

        //종료인 경우
        if(count ==5) {
            maxValue();
            return;
        }

        //백업 배열
        int[][]backup = new int[num][num];
        for(int i=0;i<num;i++) {
            backup[i] = field[i].clone();
        }

        //경우의수 구하기
        for(int i=0;i<4;i++) {

            move(i);
            dfs(count + 1);

            for(int k=0;k<num;k++) {
                field[k] = backup[k].clone();
            }

        }
    }

    public static void move(int dir) {

       switch(dir) {
            case 0: //위
                for(int i=0;i<num;i++) {
                    int index =0; //값을 넣을 인덱스 위치
                    int val=0; // 젤 최근 값
                    for(int j=0;j<num;j++) {
                        if(field[j][i]!=0){ //해당 위치의 값이 빈칸이 아닌 경우에만
                            if(field[j][i]==val) { //합쳐지는 경우
                                field[index-1][i] =(field[j][i] *2);
                                field[j][i] = 0;
                                val =0; //한번 시도에서 한번 합쳐진거는 다시 합쳐질 주 없다.
                           } else {
                                val = field[j][i];
                                field[j][i] = 0;
                                field[index][i] = val;
                                index++;
                            }
                        }
                    }
                }
                break;
            case 1: //아래
                for(int i=0;i<num;i++) {
                    int index =num-1; //값을 넣을 인덱스 위치
                    int val=0; // 젤 최근 값
                    for(int j=num-1;j>=0;j--) {
                        if(field[j][i]!=0){ //해당 위치의 값이 빈칸이 아닌 경우에만
                            if(field[j][i]==val) { //합쳐지는 경우
                                field[index+1][i] =(field[j][i] *2);
                                field[j][i] = 0;
                                val =0; //한번 시도에서 한번 합쳐진거는 다시 합쳐질 주 없다.
                            } else {
                                val = field[j][i];
                                field[j][i] = 0;
                                field[index][i] = val;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 2: //오른쪽
                for(int i=0;i<num;i++) {
                    int index =num-1; //값을 넣을 인덱스 위치
                    int val=0; // 젤 최근 값
                    for(int j=num-1;j>=0;j--) {
                        if(field[i][j]!=0){ //해당 위치의 값이 빈칸이 아닌 경우에만
                            if(field[i][j]==val) { //합쳐지는 경우
                                field[i][index+1] =(field[i][j] *2);
                                field[i][j] = 0;
                                val =0; //한번 시도에서 한번 합쳐진거는 다시 합쳐질 주 없다.

                            } else {
                                val = field[i][j];
                                field[i][j] = 0;
                                field[i][index] = val;
                                index--;
                            }
                        }
                    }
                }
                break;
            case 3: //왼쪽
                for(int i=0;i<num;i++) {
                    int index =0; //값을 넣을 인덱스 위치
                    int val=0; // 젤 최근 값
                    for(int j=0;j<num;j++) {
                        if(field[i][j]!=0){ //해당 위치의 값이 빈칸이 아닌 경우에만
                            if(field[i][j]==val) { //합쳐지는 경우
                                field[i][index-1] = (field[i][j] *2);
                                field[i][j] = 0;
                                val =0; //한번 시도에서 한번 합쳐진거는 다시 합쳐질 주 없다.

                            } else {
                                val = field[i][j];
                                field[i][j] = 0;
                                field[i][index] = val;
                                index++;
                            }
                        }
                    }
                }
                break;
        }

    }

    public static void maxValue() {

        for(int i=0;i<num;i++){
            for(int j=0;j<num;j++)
                result = Math.max(result, field[i][j]);
        }
    }

    public static void main(String []args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        num = Short.parseShort(bf.readLine());
        field = new int[num][num];
        //초기화
        for(int i=0;i<num;i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j=0;j<num;j++) {
                field[i][j]= Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);
        System.out.println(result);
    }

}

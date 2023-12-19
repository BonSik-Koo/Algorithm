package baekjoon.백트래킹;

import java.util.*;
import java.io.*;
public class 색종이붙이기_17136 {
    static int[][] map = new int[10][10];
    static int[] paper = new int[6];
    static int totalBlock = 0;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<10; j++){
                int value = Integer.parseInt(st.nextToken());
                if(value == 1){
                    totalBlock++;
                }
                map[i][j] = value;
            }
        }
        Arrays.fill(paper, 5);

        dfs(0,0,0);
        int answer = (result == Integer.MAX_VALUE)? -1 : result;
        System.out.println(answer);
    }

    public static void dfs(int row, int col, int count){
        // 끝에 도달했을 때
        // 색종이를 다 덮을 수 있어야지만 끝 지점에 도달할 수 있다.
        if(row == 9 && col == 10){
            result = Math.min(count , result);
            return;
        }

        // 열을 탐색 시 열을 증가시키므로, 열이 초과할 경우 행을 증가
        if(col >= 10){
            dfs(row + 1, 0, count);
            return;
        }

        // 가지치지(백트래킹)
        if(result != -1 && result < count){
            return;
        }

        if(map[row][col] == 1){
            // 색종이 크기 종류: 1x1, 2x2 .. 5x5
            for(int k=1; k<6; k++){
                if(paper[k] <= 0) {
                    continue;
                }

                if(isPossible(row, col, k)){
                    action(row, col, k, 0); // 덮기
                    paper[k] -= 1;

                    dfs(row, col +1, count+1);

                    action(row,col, k,1); // 복구
                    paper[k] += 1;
                }
            }
        }else{
            dfs(row, col + 1, count);
        }
    }

    public static boolean isPossible(int row, int col, int size){
        for(int i=row; i<(row+size); i++){
            for(int j=col; j<(col+size); j++){
                if(i>=10 || j>= 10 || map[i][j] == 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static void action(int row, int col, int size, int kind){
        for(int i=row; i<(row+size); i++){
            for(int j=col; j<(col+size); j++){
                map[i][j] = kind;
            }
        }
    }

}

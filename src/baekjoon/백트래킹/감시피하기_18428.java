package baekjoon.백트래킹;

import java.util.*;
import java.io.*;
public class 감시피하기_18428 {
    static char[][] arr;
    static int N;
    static boolean success = false;
    static List<int[]> Tarr = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new char[N][N];
        for(int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                char ch = st.nextToken().charAt(0);
                if(ch == 'T'){
                    Tarr.add(new int[]{i,j});
                }
                arr[i][j] = ch;
            }
        }

        dfs(0);
        System.out.println((success) ? "YES" : "NO");
    }

    static void dfs(int count){
        if(count == 3){
            success = verify();
            return;
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j] == 'X' && !success){
                    arr[i][j] = 'O';
                    dfs(count+1);
                    arr[i][j] = 'X';
                }
            }
        }
    }

    static boolean verify(){
        for(int i=0; i<Tarr.size(); i++){
            int[] T = Tarr.get(i);

            // 열 증가
            for(int y = T[1]; y<N; y++){
                if(arr[T[0]][y] == 'O') break;
                if(arr[T[0]][y] == 'S'){
                    return false;
                }
            }
            // 열 감소
            for(int y = T[1]; y>=0; y--){
                if(arr[T[0]][y] == 'O') break;
                if(arr[T[0]][y] == 'S'){
                    return false;
                }
            }

            // 행 증가
            for(int x = T[0]; x<N; x++){
                if(arr[x][T[1]] == 'O') break;
                if(arr[x][T[1]] == 'S'){
                    return false;
                }
            }

            // 행 감소
            for(int x = T[0]; x>=0; x--){
                if(arr[x][T[1]] == 'O') break;
                if(arr[x][T[1]] == 'S'){
                    return false;
                }
            }
        }
        return true;
    }

}

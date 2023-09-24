package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 구현 + dfs(조합) + 백 트래킹
 */
public class 사다리조작_15684 {
	static boolean[][] map;
	static int N;
	static int H;
	static boolean success = false;
	static int result = -1;
	
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 세로
		int M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken()); // 가로
		map = new boolean[H+1][N+1];
		
		//예외
		if(M==0){
			System.out.println(0);
			return;
		}
		
		for(int i=0; i<M;i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			map[a][b] = true;
		}
		
		findBridge(1,0);
		System.out.println(result);
	}
	
	private static void findBridge(int row, int count){
		if(count > 3){
			return;
		}
		// 조기 멈춤 조건
		if(result!=-1 && result<count){
			return;
		}
		
		//사다리 검증
		int temp = 0;
		for(int k=1; k<=N; k++){
			isSuccess(k, 1, k);
			if(!success){
				break;
			}else{
				temp++;
			}
		}
		//사다리가 성공하는 경우
		if(temp==N){
			result = (result!=-1)?Math.min(result, count):count;
			return;
		}
		
		for(int i=row; i<=H; i++){
			for(int j=1; j<N; j++){ // (j<N) 주위!!
				//사다리를 설치할 수 없는 위치
				if(map[i][j]){
					continue;
				}
				if(j>1 && map[i][j-1] || map[i][j+1]){
					continue;
				}
				
				map[i][j] = true; 	//사다리 설치
				findBridge(i, count+1);
				map[i][j] = false;
			}
		}
	}
	
	private static void isSuccess(int goalCol, int row, int col){
		//끝까지 내려왔을 때
		if(row>H){
			if(goalCol == col){
				success = true;
				return;
			}else{
				success = false;
				return;
			}
		}
		
		if(map[row][col]){
			isSuccess(goalCol, row+1, col+1);
		}else if(col>1 && map[row][col-1]){
			isSuccess(goalCol, row+1, col-1);
		}else{
			isSuccess(goalCol, row+1, col);
		}
	}
	
}

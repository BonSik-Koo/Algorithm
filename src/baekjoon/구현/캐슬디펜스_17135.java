package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 캐슬디펜스_17135 {
	static int[][] field;
	static int[] picked;
	static int N;
	static int M;
	static int D;
	static int max=0;
	
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		field = new int[N][M];
		picked = new int[3];
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++){
				field[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		pick(0,0);
		System.out.println(max);
	}
	
	private static void pick(int count, int idx){
		if(count==3){
			int result = game();
			max = Math.max(max, result);
			return;
		}
		for(int i=idx; i<M; i++){
			picked[count] = i;
			pick(count+1, i+1);
		}
	}
	private static int game(){
		int[][] status = new int[N][M];
		int count = 0;
		
		for(int line=N; line>0; line--){
			for(int pick:picked){
				for(int d=1; d<=D;d++){
					int playResult = play(line, d, pick, status);
					// 현재 거리에서 처치할 적이 없는 경우 -> 거리 늘리기
					if(playResult<0)
						continue;
					
					count+=playResult;
					break;
				}
			}
		}
		return count;
	}
	private static int play(int line, int d, int column, int[][] status){
		/**
		 * 탐색할 범위를 d 기준으로 대략 최대 거리로 잡고 계산
		 * 어차피 한번 거리를 검증해서 걸러줄거임.
		 */
		for(int col=column-d; col<=column+d; col++){
			for(int row=line-1; row>=line-d; row--){
				if(row<0 || row>=N || col<0|| col>=M)
					continue;
				if(field[row][col]==0)
					continue;
				if(Math.abs(row-line)+Math.abs(col-column)>d)
					continue;
				
				if(status[row][col]==0){ //처지하지 않은 새로운 적 위치
					status[row][col] = line;
					return 1;
				}else if(status[row][col]==line){ //같은 시간때(line) 동료가 처리했던 적 위치
					return 0;
				}
			}
		}
		
		return -1; //현재 거리(d)에 처치할 적이 없는 경우
	}
	
}

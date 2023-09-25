package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 로봇청소기_14503 {
	public static void main(String[]args) throws IOException {
		int[] dx = {-1,0,1,0};
		int[] dy = {0,1,0,-1};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken()); // 현재 x
		int c = Integer.parseInt(st.nextToken()); // 현재 y
		int d = Integer.parseInt(st.nextToken()); // 현재 바라 보는 방향
		
		int[][] field = new int[N][M];
		boolean[][] visit = new boolean[N][M];
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++){
				field[i][j] = Integer.parseInt(st.nextToken());
				visit[i][j] = false;
			}
		}
		
		int area = 0;
		while(true){
			if(!visit[r][c] && field[r][c]==0) {
				visit[r][c] = true;
				area++;
			}
			
			// 주변 4칸 중 청소할 영역이 있는지 검증
			boolean check = false;
			int nx=0, ny=0;
			for(int i=0; i<4; i++){
				nx = dx[i] + r;
				ny = dy[i] + c;
				if(0>nx || nx>=N || 0>ny || ny>=M){
					continue;
				}
				if(!visit[nx][ny] && field[nx][ny]==0){
					check = true;
					break;
				}
			}
			//청소할 구역이 있는 경우
			if(check){
				d = turn(d);
				nx = dx[d] + r;
				ny = dy[d] + c;
				
				if(0<=nx && nx<N && 0<=ny && ny<M && !visit[nx][ny] && field[nx][ny]==0){
					r = nx;
					c = ny;
				}
			}
			//청소할 구역이 없는 경우
			else{
				d = back(d);
				nx = dx[d] + r;
				ny = dy[d] + c;
				
				if(0>nx || nx>=N || 0>ny || ny>=M || field[nx][ny]==1) {
					break;
				}
				r = nx;
				c = ny;
				d = back(d); //주위! 바라보는 방향은 유지되어야 함.
			}
		}
		
		System.out.println(area);
	}
	
	private static int turn(int d){
		if(d==0){
			return 3;
		}else if(d==1){
			return 0;
		}else if(d==2){
			return 1;
		}else {
			return 2;
		}
	}
	private static int back(int d){
		if(d==0){
			return 2;
		}else if(d==1){
			return 3;
		}else if(d==2){
			return 0;
		}else {
			return 1;
		}
	}
}

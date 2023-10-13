package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 치즈_2638 {
	static int [][] paper;
	static boolean[][] visit;
	static int [][] count;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int N, M;
	
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		paper = new int[N][M];
		visit = new boolean[N][M];
		count = new int[N][M];
		for(int i=0; i<N; i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++){
				paper[i][j] = Integer.parseInt(st.nextToken());
				visit[i][j] = false;
			}
		}
		
		int time = 0;
		while(breakCheck()>0){
			verify(0,0); //가장 자리는 반드시 0 임.
			erase();
			clear();
			time++;
		}
		System.out.println(time);
	}
	
	private static int breakCheck(){
		int count = 0;
		for(int i=0; i<N; i++){
			for(int j=0; j<M;j++){
				if(paper[i][j] == 1){
					count++;
				}
			}
		}
		return count;
	}
	
	private static void clear(){
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				count[i][j] = 0;
				visit[i][j] = false;
			}
		}
	}
	
	private static void verify(int x, int y){
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx<0 || nx>=N || ny<0 || ny>=M)
				continue;
			
			if(paper[nx][ny] == 1){
				count[nx][ny]++;
				continue;
			}
			
			if(visit[nx][ny])
				continue;
			
			visit[nx][ny] = true;
			verify(nx, ny);
		}
	}
	
	private static void erase(){
		for(int i=0; i<N; i++){
			for(int j=0; j<M; j++){
				if(count[i][j]>=2){
					paper[i][j] = 0;
				}
			}
		}
	}
	
	
}

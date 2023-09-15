package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 점프왕쩰리_16173 {
	static int[][] map;
	static boolean[][] visit;
	static int N;
	static boolean check;
	
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		
		map = new int[N][N];
		visit = new boolean[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int k=0;k<N;k++){
				map[i][k] = Integer.parseInt(st.nextToken());
				visit[i][k] = false;
			}
		}
		
		dfs(0,0);
		visit[0][0] = true;
		
		String result = (check)?"HaruHaru":"Hing";
		System.out.println(result);
	}
	
	private static void dfs(int row, int col){
		if(check){
			return;
		}
		if(row==N-1 && col==N-1){
			check = true;
			return;
		}
		
		int newRow = row;
		int newCol = col;
		//경우1
		newRow = row + map[row][col];
		if(newRow<N && !visit[newRow][newCol]){
			visit[newRow][newCol] = true;
			dfs(newRow, newCol);
		}
		
		//경우2
		newRow = row;
		newCol = col + map[row][col];
		if(newCol<N && !visit[newRow][newCol]){
			visit[newRow][newCol] = true;
			dfs(newRow, newCol);
		}
	}
	
	
}

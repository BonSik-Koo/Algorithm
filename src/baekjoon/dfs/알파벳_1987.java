package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 알파벳_1987 {
	static char[][] field;
	static boolean[] visit;
	static int[] dx = {1,-1,0,0};
	static int[] dy = {0,0,1,-1};
	static int R, C;
	static int result = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		field = new char[R][C];
		visit = new boolean[27];
		for(int i=0; i<R; i++){
			String str = br.readLine();
			for(int j=0; j<C; j++){
				field[i][j] = str.charAt(j);
			}
		}
		
		visit[field[0][0]-'A'] = true;
		dfs(0,0,1);
		
		System.out.println(result);
	}
	
	public static void dfs(int x, int y, int count){
		result = Math.max(result, count);
		
		for(int i=0; i<4; i++){
			int nx = x + dx[i];
			int ny = y + dy[i];
			if(nx<0 || nx>=R || ny<0 || ny>=C)
				continue;
			if(visit[field[nx][ny]-'A'])
				continue;
			
			visit[field[nx][ny]-'A'] = true;
			dfs(nx, ny, count+1);
			visit[field[nx][ny]-'A'] = false;
		}
	}
	
}

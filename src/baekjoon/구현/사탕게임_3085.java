package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 사탕게임_3085 {
	public static void main(String[]args) throws IOException {
		int[] dx = {0,0,1,-1};
		int[] dy = {1,-1,0,0};
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char[][] field = new char[N][N];
		boolean[][] visit = new boolean[N][N];
		for(int i=0; i<N; i++){
			String str = br.readLine();
			for(int j=0; j<N; j++){
				field[i][j] = str.charAt(j);
				visit[i][j] = false;
			}
		}
		
		//완전 탐색
		int result = maxCount(field, N); //초기
		
		for(int i=0; i<N; i++){
			for(int j=0; j<N; j++){
				for(int k=0; k<4; k++){
					int nx = i + dx[k];
					int ny = j + dy[k];
					if(nx<0 || nx>=N || ny<0 || ny>=N){
						continue;
					}
					//swap
					if(!visit[nx][ny] && field[i][j]!=field[nx][ny]){
						swap(field, i, j, nx, ny);
						result = Math.max(result, maxCount(field, N));
						swap(field, i, j, nx, ny);
					}
				}
				
				visit[i][j] = true;
			}
		}
		
		System.out.println(result);
	}
	private static void swap(char[][] field, int row1, int col1, int row2, int col2){
		char temp = field[row1][col1];
		field[row1][col1] = field[row2][col2];
		field[row2][col2] = temp;
	}
	private static int maxCount(char[][] field, int N){
		int result=0;
		
		for(int i=0; i<N; i++){
			char pre = 'T';
			int count=1;
			int max = 0;
			for(int j=0; j<N; j++){
				if(pre!='T' && pre == field[i][j]) {
					count++;
				}else{
					count = 1;
				}
				pre = field[i][j];
				max = Math.max(max, count);
			}
			result = Math.max(result, max);
		}
		for(int i=0; i<N; i++){
			char pre = 'T';
			int count=1;
			int max = 0;
			for(int j=0; j<N; j++){
				if(pre!='T' && pre == field[j][i]) {
					count++;
				}else{
					count = 1;
				}
				pre = field[j][i];
				max = Math.max(max, count);
			}
			result = Math.max(result, max);
		}
		return result;
	}
}

package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 누울자리를찾아라_1652 {
	public static void main(String[]arsg) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		char [][]map = new char[N][N];
		int[][]colCash = new int[N][N];
		for(int i=0;i<N;i++){
			String str = br.readLine();
			for(int j=0;j<N;j++){
				map[i][j] = str.charAt(j);
				colCash[i][j] = 0;
			}
		}
		
		int resultRow = 0;
		int resultCol = 0;
		for(int i=0;i<N;i++){
			int rowCount = 0;
			for(int j=0; j<N; j++){
				char c = map[i][j];
				
				if(c=='X'){
					// row 게산
					if(rowCount>=2){
						resultRow++;
					}
					rowCount=0;
					
					// col 게산
					if(i!=0 && colCash[i-1][j]>=2){
						resultCol++;
					}
					continue;
				}
				
				// row 계산
				rowCount++;
				
				//colCash 게산
				if(i==0){
					colCash[i][j] = 1;
				}else {
					colCash[i][j] = colCash[i-1][j] + 1;
				}
				
				// row 게산
				if(j==N-1){
					if(rowCount>=2){
						resultRow++;
					}
				}
				// col 게산
				if(i==N-1){
					if(colCash[i][j]>=2){
						resultCol++;
					}
				}
				
			}
		}
		
		System.out.println(resultRow + " " + resultCol);
	}
}

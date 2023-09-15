package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 마인크래프트_18111 {
	static class Info{
		int time;
		int need;
		public Info(int time, int need){
			this.time = time;
			this.need = need;
		}
	}
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		//최대 높이
		int maxSize = 256;
		Info []height = new Info[maxSize+1];
		for(int i=0;i<=maxSize;i++){
			height[i] = new Info(0,0);
		}
		
		int[][]map = new int[N][M];
		int maxHeight = Integer.MIN_VALUE;
		int minHeight = Integer.MAX_VALUE;
		for(int i=0;i<N;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++){
				map[i][j] = Integer.parseInt(st.nextToken());
				maxHeight = Math.max(maxHeight, map[i][j]);
				minHeight = Math.min(minHeight, map[i][j]);
			}
		}
		
		//로직
		for(int i=0;i<N;i++){
			for(int j=0;j<M;j++){
				int h = map[i][j];

				for(int k=minHeight;k<=maxHeight;k++){
					Info info = height[k];
					
					if(k<h){
						info.time += (h-k)*2;
						info.need += (h-k);
					}else if(k>h){
						info.time += (k-h);
						info.need -= (k-h);
					}
				}
			}
		}
		
		//답 구하기
		int resultTime = Integer.MAX_VALUE;
		int resultHeight = Integer.MAX_VALUE;
		for(int i=minHeight; i<=maxHeight; i++){
			if(height[i].need + B < 0){
				continue;
			}
			
			if(resultTime >= height[i].time){
				resultTime = height[i].time;
				resultHeight = i;
			}
		}
		
		//출력
		System.out.println(resultTime+ " " + resultHeight);
	}
}

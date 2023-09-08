package baekjoon.bfs;

import java.io.*;
import java.util.*;

public class 말이되고픈원숭이_1600 {
	static int [][]arr;
	static boolean [][][]visit;
	static int []dx = {1,-1,0,0};
	static int []dy = {0,0,1,-1};
	static int []hx = {1,2,2,1,-1,-2,-2,-1};
	static int []hy = {-2,-1,1,2,2,1,-1,-2};
	static class Pos{
		int x, y, k, count;
		public Pos(int x, int y, int k, int count){
			this.x = x;
			this.y = y;
			this.k = k;
			this.count = count;
		}
	}
	
	public static void main(String[]args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int W = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		arr = new int[H][W];
		visit = new boolean[K+1][H][W];
		
		for(int i=0;i<H;i++){
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		System.out.println(bfs(0,0, W-1, H-1, K));
	}
	
	private static int bfs(int startX, int startY, int endX, int endY, int maxK){
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(startX, startY, 0,0));
		visit[0][startX][startY] = true;
		
		int result = -1;
		while(!queue.isEmpty()){
			Pos pos = queue.poll();
			int currentX = pos.x;
			int currentY = pos.y;
			int currentK = pos.k;
			int currentCount = pos.count;
			
			//break condition
			if(currentX==endX && currentY==endY){
				result = currentCount;
				break;
			}
			
			int nx=0,ny=0;
			if(currentK < maxK){
				for(int i=0;i<8;i++){
					nx = hx[i] + currentX;
					ny = hy[i] + currentY;
					if(nx<0 || nx>endX || ny<0 || ny>endY || visit[currentK+1][ny][nx] || arr[ny][nx]==1){
						continue;
					}
					queue.add(new Pos(nx, ny, currentK+1, currentCount+1));
					visit[currentK+1][ny][nx] = true;
				}
			}
			for(int i=0;i<4;i++){
				nx = dx[i] + currentX;
				ny = dy[i] + currentY;
				if(nx<0 || nx>endX || ny<0 || ny>endY || visit[currentK][ny][nx] || arr[ny][nx]==1){
					continue;
				}
				queue.add(new Pos(nx, ny, currentK, currentCount+1));
				visit[currentK][ny][nx] = true;
			}
		}
		
		return result;
	}
	
}

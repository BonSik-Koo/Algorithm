package programmers.L2;

import java.util.LinkedList;
import java.util.Queue;

public class 리코쳇로봇 {
	class Pos{
		int row, col, count;
		public Pos(int row, int col, int count){
			this.row = row;
			this.col = col;
			this.count = count;
		}
	}
	int row, col;
	char[][] map;
	boolean[][] visit;
	int[] dx = {1,-1,0,0};
	int[] dy = {0,0,1,-1};
	
	public int solution(String[] board) {
		row = board.length;
		col = board[0].length();
		map = new char[row][col];
		visit = new boolean[row][col];
		
		int startRow=0, startCol=0;
		int endRow=0, endCol=0;
		for(int i=0; i<row; i++){
			String str = board[i];
			for(int j=0; j<col; j++){
				char ch = str.charAt(j);
				if(ch=='R'){
					startRow = i;
					startCol = j;
				}else if(ch=='G'){
					endRow = i;
					endCol = j;
				}
				map[i][j] = ch;
				visit[i][j] = false;
			}
		}
		
		int answer = bfs(startRow, startCol, endRow, endCol);
		return answer;
	}
	
	private int bfs(int startRow, int startCol, int endRow, int endCol){
		Queue<Pos> queue = new LinkedList<>();
		queue.add(new Pos(startRow, startCol, 0));
		visit[startRow][startCol] = true;
		
		while(!queue.isEmpty()){
			Pos currentPos = queue.poll();
			
			if(currentPos.row==endRow && currentPos.col==endCol){
				return currentPos.count;
			}
			
			for(int i=0; i<4; i++){
				int []movePos = move(currentPos.row, currentPos.col, i);
				if(visit[movePos[0]][movePos[1]]){
					continue;
				}
				
				queue.add(new Pos(movePos[0], movePos[1], currentPos.count+1));
				visit[movePos[0]][movePos[1]] = true;
			}
		}
		
		return -1;
	}
	
	private int[] move(int x, int y, int idx){
		int nRow = x;
		int nCol = y;
		while(true){
			nRow += dx[idx];
			nCol += dy[idx];
			
			if(nRow<0 || nRow>=row || nCol<0 || nCol>=col || map[nRow][nCol]=='D'){
				nRow -= dx[idx];
				nCol -= dy[idx];
				break;
			}
		}
		return new int[]{nRow, nCol};
	}
}

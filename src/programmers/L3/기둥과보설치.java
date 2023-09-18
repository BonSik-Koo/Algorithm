package programmers.L3;

import java.util.Arrays;

public class 기둥과보설치 {
	private boolean [][] stick;
	private boolean [][] bar;
	
	public int[][] solution(int n, int[][] build_frame) {
		stick = new boolean[n+1][n+1];
		bar = new boolean[n+1][n+1];
		int count = 0;
		for(int i=0;i<=n;i++){
			Arrays.fill(stick[i], false);
			Arrays.fill(bar[i], false);
		}
		
		for(int i=0;i<build_frame.length;i++){
			int x = build_frame[i][0];
			int y = build_frame[i][1];
			int type = build_frame[i][2];
			int action = build_frame[i][3];
			
			if(type == 0){ //기둥
				if(action == 1){ //추가
					if(isInstallStick(x,y)){
						stick[x][y] = true;
						count++;
					}
				}else{ //삭제
					stick[x][y] = false;
					if(!isCanDeleted(n)){
						stick[x][y] = true;
					}else {
						count--;
					}
				}
			}else{ //보
				if(action == 1){ //추가
					if(isInstallBar(x,y)){
						bar[x][y] = true;
						count++;
					}
				}else{ //삭제
					bar[x][y] = false;
					if(!isCanDeleted(n)){
						bar[x][y] = true;
					}else{
						count--;
					}
				}
			}
		}
		
		int [][]answer = new int[count][3];
		int idx=0;
		for(int i=0;i<=n;i++){ //y
			for(int j=0;j<=n;j++){ //x
				if(stick[i][j]){
					answer[idx][0] = i;
					answer[idx][1] = j;
					answer[idx][2] = 0;
					idx++;
				}
				if(bar[i][j]){
					answer[idx][0] = i;
					answer[idx][1] = j;
					answer[idx][2] = 1;
					idx++;
				}
			}
		}
		
		return answer;
	}
	
	private boolean isInstallStick(int x, int y){
		if(y==0){ //바닥인 경우
			return true;
		}else if(y>0 && stick[x][y-1]){ //기둥위인 경우
			return true;
		}else if(x>0 && bar[x-1][y] || bar[x][y]){ //보의 한쪽 끝위 인경우
			return true;
		}
		return false;
	}
	
	private boolean isInstallBar(int x, int y){
		if(x>0 && bar[x-1][y] && bar[x+1][y]){ //양쪽 끝이 보인 경우
			return true;
		}else if(y>0 && stick[x][y-1] || stick[x+1][y-1]){ //한쪽 끝 부분이 기둥 위인 경우
			return true;
		}
		return false;
	}
	
	//기둥, 바 한개라도 못살아 남으면 false;
	private boolean isCanDeleted(int n){
		for(int x = 0;x<=n;x++){
			for(int y=0;y<=n;y++){
				if(stick[x][y] && !isInstallStick(x,y)){
					return false;
				}else if(bar[x][y] && !isInstallBar(x,y)){
					return false;
				}
			}
		}
		return true;
	}
}

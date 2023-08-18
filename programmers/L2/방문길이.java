import java.util.*;

class Solution {
    public int solution(String dirs) {
        
        int answer = 0;
        int []dx = {0,1,0,-1};
        int []dy = {1,0,-1,0};
        Boolean[][][] visit = new Boolean[11][11][4];
        for(int i=0;i<=10;i++){
            for(int j=0;j<=10;j++){
                Arrays.fill(visit[i][j], false);
            }
        }
        
        int x =5; int y=5; //마이너스 좌표 대체
        for(int i=0;i<dirs.length();i++){
            char c = dirs.charAt(i);
            int d = findPos(c);
            
            int nx = x+dx[d];
            int ny = y+dy[d];
            if(validate(nx, ny)){ //이동 가능한 좌표
                if(!visit[nx][ny][d] ){ //방문하지 않은 로드
                    answer++;
                    //양방향 방문처리
                    visit[nx][ny][d] = true; 
                    visit[x][y][(d+2)%4] = true; //0<->2, 3<->1
                }
                //좌표 업데이트
                x = nx;
                y = ny;
            }
        }
        return answer;
    }
    
    public int findPos(char c){
        int d=0;
        if(c=='U')
            d = 0;
        else if(c=='D')
            d=2;
        else if(c=='R')
            d=1;
        else
            d=3;
        return d;
    }
    public Boolean validate(int nx, int ny){
        if(nx<0 || nx>=11 || ny<0 || ny>=11)
            return false;
        return true;
    }
}

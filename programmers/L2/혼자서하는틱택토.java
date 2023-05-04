import java.math.*;

//구현 문제
class Solution {
    public class BingoGame{
        public char[][] map;
        public int totalO;
        public int totalX;
        
        public BingoGame(String[] board){
            this.map = new char[3][3];
            this.totalO = 0; this.totalX = 0;
            for(int i=0;i<board.length;i++){
                String str = board[i];
                for(int j=0;j<str.length();j++){
                    char ch = str.charAt(j);
                    map[i][j] = ch;
                    if(ch == 'O')
                        this.totalO++;
                    else if(ch=='X')
                        this.totalX++;
                }
            }
        }
        
        //정상적인 틱택토에서 나올수 있는 상황인지 판별
        public Boolean validateMap(){
            //전체 X개수가 더 많은 경우
            if(totalO<totalX){
                return false;
            }
            //전체 O의 개수가 전체 X보다 2개 이상 많은 경우
            else if(totalO-1 > totalX){
                return false;
            }
            //3x3 필드에서 전체 가질수 있는 개수를 초과한 경우
            else if(totalO>5 || totalX>=5){
                return false;
            }
            //개수에 대해선 정상적인 경우(같은 경우, O가 하나 더 많은 경우)
            else {
                 //빙고 여부 체크가 필요한 경우
                if(totalO>=3 && totalX>=3){
                    if(totalO==totalX && checkOBingo())
                        return false;
                    else if(totalO>totalX && checkXBingo()) //ex) 5,4
                        return false;
                    else 
                        return true;
                    
                }
                else{
                    return true;
                }
            }
        }
        
        //O빙고 여부 체크
        private Boolean checkOBingo(){
            int vX =0, vY =0;
            int vD1=0, vD2= 0;
            
            //가로 , 세로 대각선, 빙고 체크
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(map[i][j] == 'O')
                        vX++;
                    if(map[j][i] == 'O')
                        vY++;
                    if(i==j && map[i][j]=='O')
                        vD1++;
                    if((i==1&&j==1&&map[i][j]=='O') || (map[i][j]=='O'&&Math.abs(i-j)==2))
                        vD2++;
                }
                if(vX==3 || vY==3)
                    return true;
                vX = 0; vY = 0;
            }
            if(vD1==3 || vD2==3)
                return true;
            
            return false;
        }    
        //X빙고 여부 체크
        private Boolean checkXBingo(){
            int vX =0, vY =0;
            int vD1=0, vD2= 0;
            
            //가로 , 세로 대각선, 빙고 체크
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(map[i][j] == 'X')
                        vX++;
                    if(map[j][i] == 'X')
                        vY++;
                    if(i==j && map[i][j]=='X')
                        vD1++;
                    if((i==1&&j==1&&map[i][j]=='X') || (map[i][j]=='X'&&Math.abs(i-j)==2))
                        vD2++;
                }
                if(vX==3 || vY==3)
                    return true;
                vX = 0; vY = 0;
            }
            if(vD1==3 || vD2==3)
                return true;
            
            return false;
        }    
    }
    
    public int solution(String[] board) {
        BingoGame game = new BingoGame(board);
        int answer = (game.validateMap()==true)?1:0;
        return answer;
    }
}

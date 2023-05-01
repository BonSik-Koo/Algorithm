import java.util.*;

class Solution {
    public class Game{
        PriorityQueue<Integer> a;
        PriorityQueue<Integer> b;
        
        public Game(int[]A, int []B){
            a = new PriorityQueue<Integer>();
            b = new PriorityQueue<Integer>();
            for(int i=0;i<A.length;i++){
                a.add(A[i]); 
                b.add(B[i]);
            }
        }
        
        public int play(){
            int result = 0;
            
            while(!b.isEmpty()){
                Integer num1 = a.peek();
                Integer num2 = b.peek();
                
                //b가 승점을 가져가는 경우
                if(num1 < num2){
                    result++;
                    b.poll();
                    a.poll();
                }else {
                    b.poll();
                }
            }
            return result;
        }
    }
    
    public int solution(int[] A, int[] B) {
        
        Game game = new Game(A,B);
        int answer = game.play();
        return answer;
    }
    
  
}

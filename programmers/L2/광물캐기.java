import java.util.*;
import java.math.*;

//dfs : 완전 탐색으로 곡갱이 모든 조합 구하기
class Solution {
    public static int answer;
    public static int maxPicksNum;
    public static int[][] map = {{1,1,1}, {5,1,1}, {25,5,1}};
    public static int[] mineral; //0:dia, 1:iron, 2:stone
    
    public int solution(int[] picks, String[] minerals) {
        
        //초기화
        answer = Integer.MAX_VALUE;
        maxPicksNum = picks[0] + picks[1] + picks[2]; //총 곡갱이 수
        mineral = new int[minerals.length];
        for(int i=0;i<minerals.length;i++) {//자원에 맞는 인덱스 값으로 변환
            mineral[i] = indexConverter(minerals[i]);
        }
        dfs(picks , new int[maxPicksNum], 0);
        
        return answer;
    }
    
    //자원에 맞는 인덱스 값을 반환하는 함수
    public int indexConverter(String mineral){
        int index = 0;
        if(mineral.equals("diamond")){
            index = 0;
        }else if(mineral.equals("iron")){
            index = 1;
        }else{
            index = 2;
        }
        return index;
    }
    
    //곡갱이 순서대로 광물에 대한 피로도 구하기
    public int calcuate(int[] arr){
        int index = 0;
        int sum = 0;
        for(int i=0;i<arr.length;i++){
            int count =5; //곡갱이당 최대 자원 추출 횟수
            while(count>0){
                //남은 자원이 없는 경우
                if(index == mineral.length){            
                    return sum;
                }
                sum += map[arr[i]][mineral[index++]];
                count--;
            }
        }
        return sum;
    }
    
    //곡갱이 모든 조합 구하기
    public void dfs(int[] picks, int[]arr, int index){
        if(index == maxPicksNum){
            answer = Math.min(answer, calcuate(arr));
            return;
        }
            
        //0:다이아, 1:철, 2:돌
        for(int i=0;i<picks.length;i++){
            if(picks[i] == 0){ //곡갱이가 존재하지 않는 경우
                continue;
            }
            picks[i]--;
            arr[index] = i;
            dfs(picks, arr, index+1);
            picks[i]++;
        }
    }
    
    
}

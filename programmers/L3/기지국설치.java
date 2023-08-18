import java.util.*;

class Solution {
    public class Station {
        List<Integer> sections; //남아 있는 범위의 배열
        int flashW; //하나의 기지국이 커버할수 있는 범위
        
        public Station(int n, int[]stations, int w){
            this.sections = new ArrayList();
            this.flashW = 1+(w*2);
            
            for(int i=0;i<stations.length;i++){
                int remainSection =0;
                if(i==0) { //첫 기지국 -> 예외
                    remainSection = stations[i] - 1 - w;
                    if(remainSection>0)
                        sections.add(remainSection);
                }
                
                if(i==stations.length-1) { //마지막 기지국
                    remainSection = n - stations[i] - w;
                }else {
                    remainSection = stations[i+1] - stations[i] -1 - 2*w;
                }
                
                if(remainSection>0)
                    sections.add(remainSection);
            }
        }
        
        public int calculate(){ //필요한 기지국 개수 계산
            int result =0;
            for(int value : this.sections){
                int q = value / flashW;
                int r = value % flashW;
                result +=(q+1);
                if(r==0) //나머지가 0 -> 정확히 떨어지는 경우
                    result-=1;
            }
            return result;
        }
    }
    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        Station station = new Station(n, stations, w);
        answer = station.calculate();
     
        return answer;
    }
}

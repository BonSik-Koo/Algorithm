//자료구조(priorityQueue, statck) 문제
//구현 문제
import java.util.*;

class Solution {
    public class Work{
        String name;
        int startTimeMin; //시작 시간(분)
        int remainTime; //남은 분;
        public Work(String name, int startTimeMin, int remainTime){
            this.name = name;
            this.startTimeMin = startTimeMin;
            this.remainTime = remainTime;
        }
    }
    
    public String[] solution(String[][] plans) {
        int index = 0;
        String[] answer = new String[plans.length];
        
        PriorityQueue<Work> queue = new PriorityQueue<>(new Comparator<Work>(){
            @Override
            public int compare(Work w1, Work w2){
                return w1.startTimeMin - w2.startTimeMin;
            }
        }); //시작할 과제
        Stack<Work> remainWork = new Stack<>(); //남아있는 과제
        
        for(int i=0;i<plans.length;i++){
            String[] str = plans[i];
            queue.add(new Work(str[0], timeConverter(str[1]), Integer.valueOf(str[2])));
        }
        
        Work curWork = queue.poll(); //현재 과제
        String name = curWork.name;
        int curTime = curWork.startTimeMin;
        int reTime = curWork.remainTime;
        while(!queue.isEmpty()){
            Work nextWork = queue.poll();
            
            //새로운 과제를 진행해야하는 경우
            if(curTime + reTime > nextWork.startTimeMin){
                remainWork.push(new Work(name, curTime, curTime+reTime-nextWork.startTimeMin));
            }
            //현재 과제를 마무리 해도 되는 경우
            else if(curTime + reTime < nextWork.startTimeMin) {
                answer[index++] = name;
                curTime = curTime + reTime;
                
                //남아 있는 과제 진행
                while(!remainWork.isEmpty()){
                    Work w = remainWork.peek();
                    if(nextWork.startTimeMin > curTime + w.remainTime){
                        remainWork.pop();
                        answer[index++] = w.name;
                        curTime = curTime + w.remainTime;
                    }else if (nextWork.startTimeMin == curTime + w.remainTime){
                        remainWork.pop();
                        answer[index++] = w.name;
                    }else {
                        remainWork.pop();
                        w.remainTime = w.remainTime - (nextWork.startTimeMin - curTime);
                        remainWork.push(w);
                        break;
                    }
                }
            }
            //현재 과제 마무리 시간과 다음 과제의 시작시간이 같은 경우
            else {
                answer[index++] = name;
            }
            
            name = nextWork.name;
            curTime = nextWork.startTimeMin;
            reTime = nextWork.remainTime;
        }
        
        answer[index++] = name; //과제가 총 한개 이거나, 리스트 마지막 과제를 넣어줘야 함
        //남은 과제 순차적으로 실행
        while(!remainWork.isEmpty()){
            Work w = remainWork.pop();
            answer[index++] = w.name;
        }
        return answer;
    }
    
    //문자열 시간을 정수형 분으로 변환해주는 함수
    public int timeConverter(String time){
        String[] ti = time.split(":");
        return Integer.valueOf(ti[0])*60 + Integer.valueOf(ti[1]);
    }
}

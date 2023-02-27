package SWExpertAcademyProblem.실전실습문제.항공노선관리;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * - 다익스트라 변형 문제
 *
 * - 문제:https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AYWaiacKlHkDFAQK&categoryId=AYYpK-5qsTMDFARc&categoryType=BATTLE&battleMainPageIndex=1
 * - 힌트:https://swexpertacademy.com/main/talk/solvingClub/boardCommuView.do?solveclubId=AYWjN5DaiAsDFAQK&searchClsftn=AYWjN5DqiA0DFAQK&schClsName=Notice&searchCondition=COMMU_DETAIL-COMMU_TITLE-NICK_NAME_TAG&commuId=AYZzDUe6Ql8DFARc&orderBy=&searchKeyword=&sortingType=DATE_DESC&pageSize=10&pageIndex=1
 */
class UserSolution
{
    class Time_info { //Time 간선에 필요한 info
        int endPoint, startTime, period;
        public Time_info(int end, int startTime, int period) {
            this.endPoint = end;
            this.startTime = startTime;
            this.period = period;
        }
    }

    List<List<Time_info>> time_edge; //Time 간선
    int[][] price_edge; //Price 간선

    int[] price;
    int[][] time; //현재 시간 j 일때 i 번 노드에 도착하는 최소 시간!!! -> 다익스트라 응용(2차원배열 확장)
    int N;

    public void init(int N)
    {
        this.N = N;
        price = new int[N];
        time = new int[N][24];
        time_edge = new ArrayList<>();
        price_edge = new int[N][N];
        for(int i=0;i<N;i++) {
            time_edge.add(new ArrayList<>());
            for(int j=0;j<N;j++)
                price_edge[i][j] = Integer.MAX_VALUE;
        }

    }

    public void add(int mStartAirport, int mEndAirport, int mStartTime, int mTravelTime, int mPrice)
    {
        //time 간선 추가
        time_edge.get(mStartAirport).add(new Time_info(mEndAirport, mStartTime, mTravelTime));

        //price 간선 추가 -> 이렇게 해주지않으면 간선의 개수가 너무 많아서 시간 초과남!!!
        if(price_edge[mStartAirport][mEndAirport]!=Integer.MAX_VALUE){
            price_edge[mStartAirport][mEndAirport] =
                    Math.min(price_edge[mStartAirport][mEndAirport], mPrice);
        }else
            price_edge[mStartAirport][mEndAirport] = mPrice;

    }

    public int minTravelTime(int mStartAirport, int mEndAirport, int mStartTime)
    {
        //[0]:endPoint , [1]:도착시간, [2]:이때까지 걸린 시간,
        PriorityQueue<int[]>queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2]-o2[2]; //총 time 기준으로 오름차순
            }
        });
        for(int i=0;i<N;i++)
            for(int j=0;j<24;j++)
                time[i][j] = Integer.MAX_VALUE;
        time[mStartAirport][mStartTime] = 0;
        queue.add(new int[]{mStartAirport,mStartTime,0});

        int result = -1;
        while(!queue.isEmpty()){
            int[] current = queue.poll();

            if(time[current[0]][current[1]] < current[2])
                continue;

            if(current[0]==mEndAirport) {
                result = current[2];
                break;
            }

            for(int i = 0; i< time_edge.get(current[0]).size(); i++){
                Time_info end = time_edge.get(current[0]).get(i);
                
                //공항에서 비행기를 타기위해서 기다리는 시간
                int waitTime = (end.startTime<current[1]?end.startTime+24:end.startTime)-current[1];
                if((time[end.endPoint][current[1]] > current[2]+waitTime+end.period)){
                    time[end.endPoint][current[1]] = current[2]+waitTime+end.period;
                    queue.add(new int[]{end.endPoint, (end.startTime+end.period)%24, current[2]+waitTime+end.period});
                }
            }
        }

        return result;
    }

    //같은 지점의 노드가 있다고 했는데 Price 는 독립적이니 price 배열을 빼서 지점별 최소값만 가지게 배열로 빼서 최적화 가능!!!
    public int minPrice(int mStartAirport, int mEndAirport)
    {
        //[0]:endPoint , [1]:price
        PriorityQueue<int[]>queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1]; //price 기준으로 오름차순
            }
        });
        for(int i=0;i<N;i++)
           price[i] = Integer.MAX_VALUE;
        price[mStartAirport] = 0;
        queue.add(new int[]{mStartAirport,0});

        int result = -1;
        while (!queue.isEmpty()){
            int[] current = queue.poll();

            if(price[current[0]] < current[1]) //현재 price 가 해당 지점의 최소 price 보다 큰 경우
                continue;

            if(current[0]==mEndAirport) {//현재 지점이 도착 노드인 경우
                result = current[1];
                break;
            }

            for(int i=0;i<N;i++){
                if(price_edge[current[0]][i]== Integer.MAX_VALUE)
                    continue;
                if(current[1] + price_edge[current[0]][i] <price[i]){
                    price[i] = current[1] + price_edge[current[0]][i];
                    queue.add(new int[]{i,current[1] + price_edge[current[0]][i]});
                }
            }
        }

        return result;
    }
}
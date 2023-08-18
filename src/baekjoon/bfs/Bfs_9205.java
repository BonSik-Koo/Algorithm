package baekjoon.bfs;

import java.util.*;

public class Bfs_9205 {
    public static int storeNum;

    public static boolean bfs(Queue<String> queue,String[] store, boolean[] visit, String des) {

        while(!queue.isEmpty()) {
            String location = queue.poll();

            if(checkSuccess(location, des))
                return true;
            else {
                for(int i=0;i<storeNum; i++) {
                    if(!visit[i]) {
                        String store_location = store[i];
                        if (checkSuccess(location, store_location)) {
                            queue.add(store_location);
                            visit[i]=true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public static boolean checkSuccess(String s1, String s2){
        String[] s = s1.split(" ");
        int x1 = Integer.parseInt(s[0]);
        int y1 = Integer.parseInt(s[1]);

        String [] t = s2.split(" ");
        int x2 = Integer.parseInt(t[0]);
        int y2 = Integer.parseInt(t[1]);

        if( (Math.abs(x1-x2) + Math.abs(y1-y2)) <=1000)
            return true;

        return false;
    }
    public static void main(String[] args) {
        /*
        거리기준차이로 20개 병으로 갈수 있는지 판별 출발점에서 결승점 바로 갈수있으면 ok
        못가면 중간에 편의점 가는 걸로 하고 편의점 가는걸로 BFS로 갈수 있는지 판별하기
        */
        Queue<String> queue = new LinkedList<>(); //현재 위치를 담을 queue

        Scanner scanner = new Scanner(System.in);
        int num = Integer.parseInt(scanner.nextLine()); //횟수
        for(int i=0; i<num;i++) {
            storeNum = Integer.parseInt(scanner.nextLine());
            String start  = scanner.nextLine();

            String[] store = new String[storeNum]; //편의점의 좌표를 담는 배열
            for(int j=0;j<storeNum;j++) {
                String store_location = scanner.nextLine();
                store[j] = store_location;
            }
            boolean[] visit = new boolean[storeNum]; //편의점 방문 여부
            Arrays.fill(visit, false);

            String destination = scanner.nextLine();
            queue.add(start);
            boolean result = bfs(queue, store, visit, destination);

            if(result)
                System.out.println("happy");
            else
                System.out.println("sad");
            queue.clear();
        }
    }
}

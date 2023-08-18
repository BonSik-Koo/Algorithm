package baekjoon.bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <풀이 순서> bfs 이용
 * 1. dfs를 통해 현재 아기상어의 위치에서 동서남북 방향으로 이동하면서 탐색
 * 2.먹을수 있는 물고기의 위치는 list에 담고, 이동만 가능한 경우에는 queue에 담기
 * 3. queue 가 빌때까지 위를 반복하고 끝나면 list에 담긴 좌표에서 가장 가까운 물고기로 이동
 * 4. 이동한 물고기 좌표를 다시 queue에 넣고 위를 반복
 */
public class BFS_16236 {

    public static class loc {
        int x; int y;
        int distance;

        public loc() {};
        public loc(int x, int y, int distance) {
            this.x=x; this.y =y; this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        int num;
        int[][] map;
        int[] x = {1,-1,0,0};
        int[] y = {0,0,1,-1};
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        num = Integer.parseInt((new StringTokenizer(bf.readLine())).nextToken());
        map = new int[num][num];
        loc startFish = null;

        for (int i = 0; i < num; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < num; j++) {
                int temp = Integer.parseInt(st.nextToken());
                if (temp == 9) {
                    startFish = new loc(i, j, 0);
                    temp = 0;
                }
                map[i][j] = temp;
            }
        }

        Queue<loc> queue = new LinkedList<>();
        int nX = 0;
        int nY = 0;
        int dis = 0;
        int size = 2; //초기 상어 사이즈
        int currentEatCount = 0; //실시간으로 상어가 먹은 물고기 개수
        int result = 0; //결과
        queue.add(startFish);

        boolean[][] visit = new boolean[num][num];
        List<loc> fishEat = new ArrayList<>();

        while (true) {

            while (!queue.isEmpty()) {
                loc poll = queue.poll();

                for (int i = 0; i < 4; i++) {
                    nX = poll.x + x[i];
                    nY = poll.y + y[i];
                    dis = poll.distance + 1; //한칸씩 이동하니 거리가 1씩 멀어짐

                    if ((0 <= nX && nX < num) && (0 <= nY && nY < num) && map[nX][nY] <= size && visit[nX][nY]!=true) {

                        visit[nX][nY] =true;
                        if (map[nX][nY]>0 && map[nX][nY]<size) { //먹을수 있는 물고기가 존재하는 경우
                            fishEat.add(new loc(nX, nY, dis));
                        } else { //먹을순 없지만 이동할수 있는 경우(사이즈가 같은 경우, 빈공간인 경우)
                            queue.add(new loc(nX, nY, dis));
                        }
                    }
                }
            }

            //먹을 물고기가 더이상 존재하지 않음
            if (fishEat.isEmpty()) {
                System.out.println(result);
                return;
            }

            //먹을수 있는 물고기중 가장 가까운 물고기 찾기(먹을 물고기가 한마리 이상존재하는 경우)
            loc currentFish = fishEat.get(0);
            loc fish = null;
            for (int i = 1; i < fishEat.size(); i++) {
                fish = fishEat.get(i);

                if (fish.distance < currentFish.distance) { //더 가까운 물고기가 존재하는 경우
                    currentFish = fish;
                }
                else if(fish.distance == currentFish.distance) { //거리가 같은 경우 (row, colum 비교)
                    if(fish.x < currentFish.x)  //더 위쪽에 있는 경우
                        currentFish = fish;
                    else if(fish.x == currentFish.x){ //같은 row 일 경우
                        if(fish.y < currentFish.y)  //colum 비교
                            currentFish = fish;
                    }
                }
            }

            //상어 사이즈 업데이트
            currentEatCount++; //한마리를 먹었으니 먹은 물고기 개수 증가
            if (currentEatCount == size) { //상어 size 만큼 물고기를 먹음
                currentEatCount = 0;
                size++;
            }

            //먹을 물고기로 이동
            result += currentFish.distance;
            map[currentFish.x][currentFish.y] = 0; //먹은 자리 빈공간으로 만들기
            fishEat.clear(); //list 초기화
            for(int i =0;i<num; i++) { //방문처리 초기화
                for(int j=0;j<num; j++) {
                    visit[i][j] = false;
                }
            }
            queue.add(new loc(currentFish.x, currentFish.y, 0));

        }

    }
}

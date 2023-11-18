package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;

public class 스타트택시_19238 {
    static int N, M, fuel;
    static int[][] map;
    static int taxiRow, taxiCol;
    static boolean[] isFind;
    static MapPos[] startUserPos, endUserPos;
    static int findDistance;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        fuel = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        isFind = new boolean[M+1];
        startUserPos = new MapPos[M+1];
        endUserPos = new MapPos[M+1];
        st = new StringTokenizer(br.readLine());
        taxiRow = Integer.parseInt(st.nextToken());
        taxiCol = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            isFind[i] = false;
            startUserPos[i] = new MapPos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            endUserPos[i] = new MapPos(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        int result = start();
        System.out.println(result);
    }

    public static int start(){
        int successCount = 0;
        while(true) {
            findDistance = Integer.MAX_VALUE;
            int targetUserIndex = 0;
            successCount = 0;
            //남아있는 승객 중 가장 가까운 승객 찾기
            for (int i = 1; i <= M; i++) {
                if (isFind[i]) {
                    successCount++;
                    continue;
                }
                int result = findBFS(taxiRow, taxiCol, startUserPos[i].row, startUserPos[i].col);
                if(result == -1){ //실패한 경우(연료 부족)
                    return -1;
                }
                if(result == -2){ //조기 멈춤 조건인 경우
                    continue;
                }

                if (findDistance > result) {
                    findDistance = result;
                    targetUserIndex = i;
                } else if (findDistance == result) { //최단 거리가 같은 경우
                    if (startUserPos[targetUserIndex].row > startUserPos[i].row) { //행이 작은 걸 선택
                        findDistance = result;
                        targetUserIndex = i;
                    } else if (startUserPos[targetUserIndex].row == startUserPos[i].row) { //열이 작은 걸 선택
                        if (startUserPos[targetUserIndex].col > startUserPos[i].col) {
                            findDistance = result;
                            targetUserIndex = i;
                        }
                    }
                }
            }

            //모든 승객을 목적지로 이동시킨 경우
            if(successCount == M){
                break;
            }

            //승객 거리만큼 연료 차감
            fuel -= findDistance;
            if (fuel <= 0) { //연료가 바닥나는 경우, 등호 주의(목적지까지 갈수가 없음)
                return -1;
            }

            //택시를 승객 위치로 이동
            taxiRow = startUserPos[targetUserIndex].row;
            taxiCol = startUserPos[targetUserIndex].col;

            //목적지 까지 최단 거리 찾기
            findDistance = Integer.MAX_VALUE;
            int endDistance = findBFS(taxiRow, taxiCol, endUserPos[targetUserIndex].row,
                    endUserPos[targetUserIndex].col);
            if(endDistance == -1){ //실패한 경우
                return -1;
            }

            //목적지 거리만큼 연료 차감
            fuel -= endDistance;
            if (fuel < 0) { //연료가 바닥나는 경우
                return -1;
            }

            taxiRow = endUserPos[targetUserIndex].row;
            taxiCol = endUserPos[targetUserIndex].col;
            fuel += (endDistance * 2); //연료 충전
            isFind[targetUserIndex] = true;
        }

        return fuel;
    }

    // 연료가 떨어질 경우 -> 종료 되어야 하는 경우 -1
    // 찾은 최소 거리보다 클 경우 -> 더이상 탐색할 필요가 없는 경우 -2
    public static int findBFS(int startRow, int startCol, int targetRow, int targetCol) {
        Queue<SearchPos> queue = new LinkedList<>();
        boolean[][] check = new boolean[N+1][N+1];
        queue.add(new SearchPos(startRow, startCol, 0));
        check[startRow][startCol] = true;

        while (!queue.isEmpty()) {
            SearchPos pos = queue.poll();
            if (pos.row == targetRow && pos.col == targetCol) {
                return pos.distance; //최단 거리를 찾은 경우
            }

            if(fuel - pos.distance < 0){ //실패한 경우
                return -1;
            }
            if(findDistance != Integer.MAX_VALUE && findDistance < pos.distance){ //조기 멈춤 조건
                return -2;
            }

            for (int i = 0; i < 4; i++) {
                int nx = pos.row + dx[i];
                int ny = pos.col + dy[i];
                if (nx <= 0 || nx > N || ny <= 0 || ny > N || map[nx][ny]==1 || check[nx][ny]) {
                    continue;
                }
                queue.add(new SearchPos(nx, ny, pos.distance + 1));
                check[nx][ny] = true;
            }
        }
        return -1; //경로를 찾지 못한 경우
    }

    static class SearchPos {
        int row, col, distance;
        public SearchPos(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }
    static class MapPos{
        int row, col;
        public MapPos(int row, int col){
            this.row = row;
            this.col = col;
        }
    }

}

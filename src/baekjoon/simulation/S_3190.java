package baekjoon.simulation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class S_3190 {

    public static void main(String[] args) throws IOException {

        int num = 0;
        int appleCount = 0;
        int turnCount = 0;
        boolean[][] appleMap; // 사과 위치
        Map<Integer, String> turnMap = new LinkedHashMap<>(); //방향전환을 담는 변수
        int time = 0; //경과 시간을 담는 변수

        class snackPos {
            int row;
            int col;

            public snackPos(int row, int col) {
                this.row = row;
                this.col = col;
            }
        }
        boolean[][] snack; //뱀의 있는 위치를 체크하기 위한 변수
        List<snackPos> snackList = new ArrayList(); //뱀이 위치한 포지션을 담고있는 변수 -> 꼬리를 쉽게 줄이기 위해서 사용
        Queue<snackPos> queue = new LinkedList<>(); //실시간 뱀의 머리의 위치를 담는 변수
        int[] x = {0, -1, 0, 1}; //오,위,왼,아래
        int[] y = {1, 0, -1, 0};

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        //초기화
        num = Integer.parseInt(bf.readLine());
        snack = new boolean[num][num];
        appleMap = new boolean[num][num];
        appleCount = Integer.parseInt(bf.readLine());

        for (int i = 0; i < appleCount; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;

            appleMap[row][col] = true;
        }
        turnCount = Integer.parseInt(bf.readLine());
        for (int i = 0; i < turnCount; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int t = Integer.parseInt(st.nextToken());
            String dir = st.nextToken(); //turn 방향

            turnMap.put(t, dir);
        }

        //메인 로직
        queue.add(new snackPos(0, 0));
        snack[0][0] = true; //처음에는 뱀의 머리와 꼬리 위치
        snackList.add(new snackPos(0, 0));

        int direction = 0; //처음 방향은 오른쪽
        while (!queue.isEmpty()) {
            snackPos pollSnack = queue.poll();

            //머리를 한칸 이동
            int newX = pollSnack.row + x[direction];
            int newY = pollSnack.col + y[direction];
            time++; //경과 시간 증가

            //종료조건
            if (((0 > newX || newX >= num) || (0 > newY) || newY >= num) || (snack[newX][newY] == true))
                break;

            snack[newX][newY] = true; //이동한 뱀 머리를 방문처리
            snackList.add(new snackPos(newX, newY)); //새로운 뱀의 머리 넣기

            //사과가 없는 경우
            if (appleMap[newX][newY] != true) {

                snackPos remove = snackList.remove(0);
                snack[remove.row][remove.col] = false;
            } else { //사과가 있는 경우
                appleMap[newX][newY] = false; //먹은 표시시
            }

            //방향 전환 검사
            if (turnMap.get(time) != null) {
                String dir = turnMap.get(time);
                if (dir.equals("D")) //오른쪽 방향 전환
                    direction = (direction + 3) % 4;
                else //왼쪽 방향 전환
                    direction = (direction + 1) % 4;
            }

            queue.add(new snackPos(newX, newY));
        }

        System.out.println(time);
    }
}

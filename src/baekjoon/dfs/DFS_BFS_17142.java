package baekjoon.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * <풀이방법></풀이방법>
 * 1. 처음 활성화된 바이러스들중 지정된 개수만큼 선택될수 있는 경우의 수를 dfs로 탐색
 * 2. 선택된 바이러스들을 BFS로 탐색하고 값(경과요일)을 바로바로 map에 업데이트 하면서 최대 시간 계속 업데이트
 * 3. 선택된 바이러스들로 바이러스를 퍼트렸을때 최대경과일을 계속 비교
 * 4. 주의! 바이러스지만 비활성화된 바이러스의 대해서는
 *    - 해당 바이러스를 만났을때 모든 빈칸이 바이러스로 퍼졌으면 해당 바이러스를 더이상 활성화하지않음
 *    - 해당 바이러스를 만났을때 아직 남아있는 빈칸이 있다면 해당 바이러스를 활성화 시켜 계속 퍼트리게 진행
 */
public class DFS_BFS_17142 {

    static int num=0;
    static int virusCount=0;
    static int emptyConunt =0;
    static int[][]map;
    static int[]x = {1,-1,0,0};
    static int[]y = {0,0,1,-1};
    static int result = Integer.MAX_VALUE;

    static List<virus> virusList = new ArrayList<>();
    static List<virus> selectVirusList = new ArrayList<>();
    static class virus {
        int row; int col; int time;

        public virus(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    public static void dfs(int index, int count) {

        if(count == virusCount) {
            int maxTime = bfsVirusSpread();
            if(maxTime!=-1)
                result = Math.min(result,maxTime);
            return;
        }

        for(int i=index;i<virusList.size(); i++ ) {

            selectVirusList.add(virusList.get(i));
            dfs(i+1,count+1);
            selectVirusList.remove(virusList.get(i));
        }

    }

    public static int bfsVirusSpread() {
        Queue<virus> queue = new LinkedList<>();
        int[][] copyMap = new int[num][num];
        int maxTime = Integer.MIN_VALUE;
        int newRow =0; int newCol =0; int newTime =0;
        int count =0;

        //초기화
        for(int i=0;i<num;i++) {
            for(int j=0;j<num;j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        for(int i=0;i<selectVirusList.size();i++ ) {
            virus virus = selectVirusList.get(i);
            copyMap[virus.row][virus.col] = -1; //바이러스 활성화
            queue.add(virus);
        }

        while(!queue.isEmpty()) {

            virus pollVirus = queue.poll();
            maxTime =Math.max(maxTime, pollVirus.time);

            if(count == emptyConunt)
                continue;

            for(int i=0;i<4;i++) {
                newRow = pollVirus.row+ x[i];
                newCol = pollVirus.col + y[i];
                newTime = pollVirus.time+1;

                //유망성 검사
                if((0<=newRow && newRow<num) && (0<=newCol && newCol<num)) {

                    if( copyMap[newRow][newCol]==0) {//빈칸일때
                        count++;
                        copyMap[newRow][newCol] = newTime;
                        queue.add(new virus(newRow, newCol, newTime));
                    }
                    else if(copyMap[newRow][newCol]==-2) { //기존에 바이러스지만 비활성화된 바이러스
                        if(count == emptyConunt)
                            continue;
                        copyMap[newRow][newCol] = newTime;
                        queue.add(new virus(newRow, newCol, newTime));
                    }
                }
            }

        }

        //모든 빈칸에 바이러스가 모두 퍼진지 확인
        if(count != emptyConunt)
            return -1;

        return maxTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        num = Integer.parseInt(st.nextToken());
        virusCount = Integer.parseInt(st.nextToken());
        map = new int[num][num];

        for(int i=0;i<num;i++) {
            st = new StringTokenizer(bf.readLine(), " ");

            for(int j=0;j<num;j++) {
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 2) {
                    virusList.add(new virus(i, j, 0));
                    temp = -2; //모든 바이러스 비활성로 세팅
                }
                else if(temp == 0)
                    emptyConunt++;
                map[i][j] = temp;
            }
        }

        dfs(0,0);
        if(result == Integer.MAX_VALUE) //모든 바이러스들의 경우로 조합했을때 빈칸에 바이러스를 모두 퍼트릴수 없을때
            result = -1;
        System.out.println(result);
    }
}

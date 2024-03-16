package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 톱니바퀴_14891 {
    static List<Integer>[] map = new List[4];
    static int[] rot = new int[4];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            String state = br.readLine();
            List<Integer> list = new ArrayList<>();
            for(int j=0; j<8; j++) {
                list.add(state.charAt(j) - '0');
            }
            map[i] = list;
        }

        int K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int number = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken());

            play(number, dir);
        }

        System.out.println(getResult());
    }

    static void play(int number, int dir) {
        rotation(number, dir);
        rot[number] = dir;

        // 회전 톱니바퀴 왼쪽 탐색
        for(int i=number-1; i>=0; i--) {
            if(rot[i+1] == 0) { // 톱니바퀴가 이동하지 않은 경우
                rot[i] = 0;
                continue;
            }

            List<Integer> target = map[i];
            List<Integer> compare = map[i+1];
            int compareVal = (rot[i+1] == 1) ? compare.get(7) : compare.get(5);
            if(target.get(2) == compareVal) {
                rot[i] = 0;
                continue;
            }

            int targetDir = (rot[i+1] == 1) ? -1 : 1;
            rotation(i, targetDir);
            rot[i] = targetDir;
        }
        // 회전 톱니바퀴 오른쪽 탐색
        for(int i=number+1; i<4; i++) {
            if(rot[i-1] == 0) {
                rot[i] = 0;
                continue;
            }

            List<Integer> target = map[i];
            List<Integer> compare = map[i-1];
            int compareVal = (rot[i-1] == 1) ? compare.get(3) : compare.get(1);
            if(target.get(6) == compareVal) {
                rot[i] = 0;
                continue;
            }

            int targetDir = (rot[i-1] == 1) ? -1 : 1;
            rotation(i, targetDir);
            rot[i] = targetDir;
        }
    }
    static void rotation(int number, int dir) {
        List<Integer> cur = map[number];
        if(dir == 1) { // 시계 방향 = 1
            cur.add(0, cur.remove(cur.size() - 1));
        } else { // 반시계 = -1
            cur.add(cur.remove(0));
        }
    }

    static int getResult() {
        int result = 0;
        int val = 1;
        for(int i=0; i<4; i++) {
            result += (map[i].get(0) == 1) ? val : 0;
            val *= 2;
        }
        return result;
    }

}

package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 전구와스위치_2138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String origin = br.readLine();
        String goal = br.readLine();

        boolean[] originalStateA = new boolean[N];
        boolean[] originalStateB = new boolean[N];
        boolean[] goalState = new boolean[N];
        for(int i=0; i<origin.length(); i++) {
            originalStateA[i] = origin.charAt(i) != '0';
            originalStateB[i] = origin.charAt(i) != '0';
            goalState[i] = goal.charAt(i) != '0';
        }

        int resultA = find(N, originalStateA, goalState, 0);
        int resultB = find(N, pushSwitch(0, 1, originalStateB), goalState, 1);
        if(resultA == Integer.MAX_VALUE && resultB == Integer.MAX_VALUE) {
            System.out.println(-1);
        }else{
            System.out.println(Math.min(resultA, resultB));
        }
    }

    static int find(int N, boolean[] originalState, boolean[] goalState, int count) {
        for(int i=1; i<N; i++) {
            if((originalState[i-1] != goalState[i-1]) && i!=N-1) {
                pushSwitch(i-1, i+1, originalState);
                count++;
            } else if ((originalState[i-1] != goalState[i-1]) && i==N-1) {
                pushSwitch(i-1, i, originalState);
                count++;
            }
        }

        for(int i=0; i<N; i++) {
            if(originalState[i] != goalState[i]) {
                return Integer.MAX_VALUE; // 만들수 없는 경우
            }
        }
        return count;
    }

    static boolean[] pushSwitch(int toIdx, int fromIdx, boolean[] state) {
        for(int i=toIdx; i<=fromIdx; i++) {
            state[i] = !state[i];
        }
        return state;
    }

}

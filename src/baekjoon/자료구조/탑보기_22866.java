package baekjoon.자료구조;

import java.io.*;
import java.util.*;

public class 탑보기_22866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[][] answer = new int[N+1][2]; // 개수, 최소 인덱스 값
        Stack<int[]> stack = new Stack<>();

        // 앞에서 stack 연산 한번
        for(int i=1; i<=N; i++) {
            int height = arr[i];

            while(!stack.isEmpty()) {
                int[] building = stack.peek();
                if(building[0] <= height) {
                    stack.pop();
                } else {
                    break;
                }
            }
            answer[i][0] += stack.size();
            if(!stack.isEmpty()) {
                int[] firstBuilding = stack.peek();
                answer[i][1] = firstBuilding[1];
            }

            stack.add(new int[]{height, i});
        }

        // 뒤에서 stack 연산 한번
        stack = new Stack<>();
        for(int i=N; i>=1; i--) {
            int height = arr[i];

            while(!stack.isEmpty()) {
                int[] building = stack.peek();
                if(building[0] <= height) {
                    stack.pop();
                } else {
                    break;
                }
            }
            answer[i][0] += stack.size();
            if(!stack.isEmpty()) {
                int[] firstBuilding = stack.peek();
                if(answer[i][1] == 0) {
                    answer[i][1] = firstBuilding[1];
                } else {
                    if(Math.abs(answer[i][1]-i) > Math.abs(i-firstBuilding[1])) {
                        answer[i][1] = firstBuilding[1];
                    }
                }

            }
            stack.add(new int[]{height, i});
        }

        StringBuilder result = new StringBuilder();
        for(int i=1; i<=N; i++) {
            result.append(answer[i][0] + " ");
            if(answer[i][0] == 0) {
                result.append("\n");
            } else{
                result.append(answer[i][1] + "\n");
            }
        }

        System.out.println(result.toString());
    }
}

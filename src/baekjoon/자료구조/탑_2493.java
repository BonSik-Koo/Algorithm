package baekjoon.자료구조;

import java.io.*;
import java.util.*;

public class 탑_2493 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack<int[]> stack = new Stack<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1; i<=N; i++) {
            int value = Integer.parseInt(st.nextToken());

            int idx = -1;
            while(!stack.isEmpty()) {
                int[] peekValue = stack.peek();
                if(peekValue[0] < value){
                    stack.pop();
                } else if(peekValue[0] > value) {
                    idx = peekValue[1];
                    break;
                } else {
                    idx = peekValue[1];
                    stack.pop();
                    break;
                }
            }

            if(idx != -1) {
                sb.append(idx + " ");
            } else {
                sb.append("0 ");
            }
            stack.add(new int[]{value, i});
        }

        System.out.println(sb.toString());
    }
}

package baekjoon.자료구조;

import java.io.*;
import java.util.*;

public class 오등큰수_17299 {
    private static final int MAX_SIZE = 1_000_001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] index = new int[N];
        int[] count = new int[MAX_SIZE];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            int value = Integer.parseInt(st.nextToken());
            index[i] = value;
            count[value]++;
        }

        int[] result = new int[N];
        Stack<Integer> stack = new Stack<>();
        for(int i=0; i<N; i++){
            while(!stack.isEmpty() && count[index[i]]>count[index[stack.peek()]]) {
                result[stack.pop()] = index[i];
            }
            stack.push(i);
        }

        // 오등큰수가 없는 인덱스들은 -1로 처리
        while(!stack.isEmpty()) {
            result[stack.pop()] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<N; i++) {
            sb.append(result[i] + " ");
        }
        System.out.println(sb.toString());
    }
}

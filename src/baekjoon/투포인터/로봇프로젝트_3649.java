package baekjoon.투포인터;

import java.util.*;
import java.io.*;

public class 로봇프로젝트_3649 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;

        while((input = br.readLine()) != null) {
            int x = Integer.parseInt(input) * 10000000;
            int n = Integer.parseInt(br.readLine());

            int[] piece = new int[n];
            for(int i=0; i<n; i++) {
                piece[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(piece);

            int start = 0;
            int end  = n-1;
            int[] answer = new int[2];
            while(start < end) {
                int sum = piece[start] + piece[end];

                if(sum > x) {
                    end--;
                } else if (sum < x){
                    start++;
                } else {
                    // 처음 찾은 답이 최대 차이
                    answer[0] = piece[start];
                    answer[1] = piece[end];
                    break;
                }
            }

            if(answer[0] == 0 && answer[1] == 0) {
                System.out.println("danger");
            } else {
                System.out.println("yes " + answer[0] + " " + answer[1]);
            }

            input = null;
        }
    }
}

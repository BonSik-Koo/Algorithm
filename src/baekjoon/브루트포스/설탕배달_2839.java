package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 설탕배달_2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int result = -1;
        for(int i=(N/5); i>=0; i--){
            int remain = (N - 5*i);
            if(remain%3==0){
                result = i + (remain/3);
                break;
            }
        }

        System.out.println(result);
    }
}

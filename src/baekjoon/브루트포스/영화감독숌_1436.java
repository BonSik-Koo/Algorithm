package baekjoon.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 영화감독숌_1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int initValue = 666;
        int count = 1;

        while(count != N){
            initValue++;
            if(String.valueOf(initValue).contains("666")){
                count++;
            }
        }
        System.out.println(initValue);
    }

}

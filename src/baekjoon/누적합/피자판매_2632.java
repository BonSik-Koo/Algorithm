package baekjoon.누적합;

import java.util.*;
import java.io.*;
public class 피자판매_2632 {
    static int a, b;
    static int[] arrA, arrB;
    static int[] sumACnt, sumBCnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int c = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());

        // 원형 큐에서 누적합을 쉽게 구하기 위해 배열을 2배 늘린다.
        arrA = new int[a*2];
        arrB = new int[b*2];
        sumACnt = new int[20000001];
        sumBCnt = new int[20000001];

        int maxSum = 0;
        for(int i=0; i<a; i++){
            int s = Integer.parseInt(br.readLine());
            arrA[i] = s;
            arrA[i+a] = s;
            maxSum += s;
        }
        sumACnt[0] = 1;
        sumACnt[maxSum] = 1;

        maxSum = 0;
        for(int i=0; i<b; i++){
            int s = Integer.parseInt(br.readLine());
            arrB[i] = s;
            arrB[i+b] = s;
            maxSum += s;
        }
        sumBCnt[0] = 1;
        sumBCnt[maxSum] = 1;

        System.out.println(findCnt(c));
    }

    static int findCnt(int c){
        calculateSumCnt(a, arrA, sumACnt);
        calculateSumCnt(b, arrB, sumBCnt);

        int answer = 0;
        for(int i=0; i<=c; i++){
            answer += (sumACnt[i] * sumBCnt[c-i]);
        }
        return answer;
    }

    static void calculateSumCnt(int size, int[]arr, int[]sumArrCnt){
        for(int i=0; i<size; i++){
            int sum = 0;
            for(int k=i; k<(i+size-1); k++){ // 순환 큐이기 때문에, 모두 더한 경우가 겹치므로, 모든 경우의 합은 별도 계산
                sum += arr[k];
                sumArrCnt[sum] += 1;
            }
        }
    }

}

package SWExpertAcademyProblem.분할정복8차시.기본문제.InversionCounting패키지;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 분할정복 - merge sort 응용
 *
 * - merge_sort 직접 구현
 * - 인덱스를 오른쪽에서 왼쪽으로 비교함 - 응용!
 */
public class InversionCounting {

    public static int[]arr , temp;
    public static int N;
    public static long result =0; //이거 신경!!!

    public static void main(String args[]) throws Exception
    {
        System.setIn(new java.io.FileInputStream("src/SWExpertAcademyProblem/분할정복8차시/res/input3.txt"));
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T;
        T=Integer.parseInt(bf.readLine());

        for(int test_case = 1; test_case <= T; test_case++)
        {
            //초기화
            N = Integer.parseInt(bf.readLine());
            arr = new int[N]; temp = new int[N];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int i=0;i<N;i++)
                arr[i] = Integer.parseInt(st.nextToken());

            //merge
            merge(0,N);

            System.out.println("#"+test_case+" "+result);
            result =0;
        }
    }
    public static void merge(int begin, int end) {
        if(end-begin<=1) //원소가 한개인 경우
            return;
        int mid = begin + (end-begin)/2;
        merge(begin, mid);
        merge(mid, end);
        merge_check(begin,mid,end);
    }
    public static void merge_check(int begin, int mid, int end){

        int begin1 = mid-1; int end1 = begin-1;
        int begin2 = end-1; int end2 = mid-1;

        int index =end-1;
        while (begin1!=end1 && begin2!=end2){

            if(arr[begin1] > arr[begin2]){ //역전 현상이 일어난 경우
                result += (begin2-end2);
                temp[index--] = arr[begin1--];
            }else {
                temp[index--] = arr[begin2--];
            }
        }
        while(begin1!=end1)
            temp[index--] = arr[begin1--];
        while (begin2!=end2)
            temp[index--] = arr[begin2--];

        for(int i=begin;i<end;i++)
            arr[i] = temp[i];
    }
}

package baekjoon.누적합;

import java.io.*;
import java.util.*;

public class 두배열합_2143 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        int[] b = new int[m];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<m; i++) {
            b[i] = Integer.parseInt(st.nextToken());
        }

        // 연속 부분합 구하기 -> O(n^2)
        List<Long> sumA = findSumAndSort(a, n);
        List<Long> sumB = findSumAndSort(b, m);

        // 조합 개수 구하기(투 포인터) -> O(n^2)
        long result = findResult(sumA, sumB, T);
        System.out.println(result);
    }

    static List<Long> findSumAndSort(int[] a, int size) {
        List<Long> sumArr = new ArrayList<>(); // 최대 사이즈 size*(size-1)/2
        int idx = 0;
        for(int i=0; i<size; i++) {
            long sum = 0;
            for(int j=i; j<size; j++) {
                sum += a[j];
                sumArr.add(sum);
            }
        }

        Collections.sort(sumArr); // 투포인터/이분탐색을 위해서 정렬
        return sumArr;
    }

    static long findResult(List<Long> arrA, List<Long> arrB, int T) {
        long result = 0;
        int left = 0;
        int right = arrB.size()-1;

        while(left<arrA.size() && right>=0) {
            long valueA = arrA.get(left);
            long valueB = arrB.get(right);
            long sum = valueA + valueB;
            if(sum == T) {
                // 각 A, B 배열에서 동일한 값 개수 구하기
                long cntA = 0, cntB = 0;
                while(left<arrA.size() && arrA.get(left)==valueA) {
                    cntA++;
                    left++;
                }
                while(right>=0 && arrB.get(right)==valueB) {
                    cntB++;
                    right--;
                }
                result += (cntA * cntB);
            } else if(sum > T) {
                right--;
            } else {
                left++;
            }
        }

        return result;
    }
}

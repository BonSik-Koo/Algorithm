package baekjoon.이분탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이와가위_20444 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        // 짜르는 횟수 기준 -> 가로
        // 서로 대칭 모양이므로 절반까지만 탐색
        boolean result = binarySearch(n, n/2, k);

        System.out.println(result ? "YES" : "NO");
    }

    private static boolean binarySearch(long n, long size, long k) {
        long low = 0;
        long high = size;
        boolean success = false;

        while(low <= high) {
            long mid = (low + high) / 2;
            long piece = getPiece(n, mid);

            if(piece <= k) {
                low = mid + 1;
                if(piece == k) {
                    success = true;
                    break;
                }
            } else {
                high = mid - 1;
            }
        }

        return success;
    }

    private static long getPiece(long n, long count) {
        // count : 가로로 짜를 횟수
        long y = count + 1; // 세로 조각
        long x = (n - count) + 1; // 가로 조각
        return x * y;
    }

}

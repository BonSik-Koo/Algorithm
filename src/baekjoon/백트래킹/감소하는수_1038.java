package baekjoon.백트래킹;

import java.util.*;

public class 감소하는수_1038 {
    static List<Long> nums = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        if (N <= 10) { // N이 곧 정답인 경우
            System.out.println(N);
        }
        // 최대 증가하는 수 : 9876543210
        // (0 ~ 9) 에서 각 자리가 포함/미포함 여부가 즉 감소 숫자의 최대 개수 : 2^10 - 1
        // 0을 0번째로 취급하기 때문에, 번째로는 최대 개수-1 까지
        else if (N > 1022) {
            System.out.println(-1);
        } else {
            for (int i = 0; i < 10; i++) {
                dfs(i, 0);
            }
            Collections.sort(nums);
            System.out.println(nums.get(N));
        }

    }

    // ex) 3 -> (30, 31, 32) -> (310, 320, 321, ...)
    public static void dfs(long num, int count) {
        if (count >= 10) {
            return;
        }
        nums.add(num);

        // 다음 자릿수에 올 수 있는 수
        for (int i = 0; i < (num % 10); i++) {
            dfs((num * 10) + i, count + 1);
        }
    }

}

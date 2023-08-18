package programmers.L2;

import java.util.Arrays;

/**
 * 1. 왼쪽 부터 시작
 * 2. 각 자리에 알파벳을 찾기위해서 2가지 경우 경우 구하기
 * 3. 그 다음 커서로 넘어가면서 바꿔야 되는 알파벳 판별
 *     -> 만약 바꿔야 되면 해당 위치로 이동하기 위해서 2가지 방식중 가까운 방법 찾기
 * 4. 반복!
 *
 *
 * 풀지 못함!!!!!!!!!!!!!
 * 음....2022부터 바뀌고 나서 이상한데......
 */
public class 조이스틱 {

    public static int solution(String name) {
        int answer = 0;

        char[]array = new char[name.length()];
        Arrays.fill(array, 'A');

        int index =0;
        for(int i=0;i<name.length();i++) {
            char c1 = name.charAt(i);
            char c2 = array[i];

            if(c1!=c2) {
                int min_shift = Math.min(Math.abs(index - i), Math.abs(0 - index) + Math.abs(name.length() - 1 - i) + 1);

                int min = Math.min(Math.abs(c1 - c2), 26-Math.abs(c1-c2));

                System.out.println("min_shift:" + min_shift + " min: " + min);
                answer+=min_shift;
                answer+=min;
                index=i;
            }
        }

        return answer;
    }

    public static void main(String[]args) {

        String name = "AAAAABBAAAAAAABAAA";
        int solution = solution(name);
        System.out.println(solution);
    }

}

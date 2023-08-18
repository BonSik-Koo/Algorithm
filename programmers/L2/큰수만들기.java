import java.util.*;

/**
 * 그리디 풀이(DFS로도 풀수 있을듯)
 * 다시 풀기
 */
public class 큰수만들기 {

    public static String solution(String number, int k) {
        String answer = "";

        int count =0;
        Deque<Character> list = new LinkedList<>();

        for(int i=0;i<number.length();i++) {
            char c1 = number.charAt(i);

            if(!list.isEmpty() && count!=k) {
                while (!list.isEmpty()) {
                    if(list.getLast() < c1) {
                        list.removeLast();
                        count++;
                        if(count == k)
                            break;
                    }else   // 1 2 3.. 등으로 형성자체가 안되니
                        break;
                }
                list.addLast(c1);

            }else //비어있거나 삭제될 횟수를 채운경우
                list.addLast(c1);
        }

        while(count != k) { //6 5 4 3 2 1 등으로 되있는 경우를 위해서
            list.removeLast();
            count++;
        }

        StringBuilder sb = new StringBuilder();
        list.stream().forEach(c -> sb.append(c));
        answer = sb.toString();
        return answer;
    }

    public static void main(String[]args) {

        String number = "654321";
        int k = 1;
        String solution = solution(number, k);
        System.out.println(solution);
    }

}

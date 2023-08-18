import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 다시 풀기!
 * <풀이방법>
 * 1. 주어진 숫자 배열을 처음부터 하나씩 시작한다.
 * 2.1 만약 현재 숫자가 그전숫자보다 작은 경우에는 stack의 맨끝에 넣는다 (전숫자가 더 크면 더 큰값이기 때문에)
 * 2.2 만약 더 큰 경우라면 stack 에서 현재 숫자보다 작은 값들을 지우는것이 더큰값이기 때문에 stack의 맨끝에서 부터 비교하여 작으면 삭제한다.
 * 3.1 2과정에서 count가 k와 같아지면 종료하고 남은 숫자들을 stack 끝에 차례대로 넣는다.
 * 3.2 주어진 숫자 배열을 모두 탐색하였는데 count가 k가 되지 않았으면 k-count 만큼 stack의 맨끝에서 부터 차례대로 없앤다.
 */
public class G_2812 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        Deque<Integer> stack = new ArrayDeque<>();

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        String s = bf.readLine();
        int priorNum =0;
        int count=0;
        boolean check = false;
        String result = null;
        for(int i=0;i<N;i++) {
            int value = Character.getNumericValue(s.charAt(i));

            if(check!=true && priorNum < value) {
                while ( !stack.isEmpty() && stack.getLast()< value) {
                    stack.removeLast();
                    count++;
                    if(count == k){
                        check = true;
                        break;
                    }
                }
            }
            priorNum = value;
            stack.offerLast(value);
        }
        if(count!=k) {
            while(count<k){
                stack.removeLast();
                count++;
            }
        }

        stack.stream().forEach(e-> System.out.print(e));
    }
}

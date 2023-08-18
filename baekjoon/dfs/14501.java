import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <풀이순서>
 * 1. dfs로 경우의 수를 구한다.
 * 2. 경우에 따른 금액 최대값을 구한다.
 */
public class DFS_14501 {
    static int num =0;
    static int result =Integer.MIN_VALUE;
    static List<consulting> list = new ArrayList<>();

    static class consulting {
        int period;
        int price;

        public consulting(int period, int price) {
            this.period = period;
            this.price = price;
        }
    }

    public static void dfs(int index, int totalPrice) {

        for(int i=index ; i<num; i++) {
            consulting consulting = list.get(i);
            int newIndex = i + consulting.period -1 ;
            int newTotalPrice = totalPrice + consulting.price;
            if(newIndex >= num-1) {
                if(newIndex == num-1)
                    result = Math.max(result, newTotalPrice);
                result = Math.max(result, totalPrice);
            }else {
                dfs(newIndex+1, newTotalPrice);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        num = Integer.parseInt(bf.readLine());
        int per =0; int pri =0;

        for(int i=0;i<num;i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            per = Integer.parseInt(st.nextToken());
            pri = Integer.parseInt(st.nextToken());

            list.add(new consulting(per, pri));
        }

        dfs(0, 0);
        System.out.println(result);
    }
}

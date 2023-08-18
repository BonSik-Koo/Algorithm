import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G_1715 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());
        Queue<Integer> queue = new PriorityQueue<>();
        for(int i=0;i<N;i++)
            queue.add(Integer.parseInt(bf.readLine()));

        int result =0;
        while(queue.size()>=2) {
            Integer minNum1 = queue.poll();
            Integer minNum2 = queue.poll();
            result += minNum1 + minNum2;
            queue.add(minNum1 + minNum2);
        }

        System.out.println(result);
    }
}

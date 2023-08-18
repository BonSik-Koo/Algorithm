import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bfs_1697 {
    static class pos {
        int loc;
        int level;
        public pos(int loc1, int level1) {
            loc = loc1 ; level = level1;
        }
    }

    public static int bfs(Queue<pos> queue,boolean[] check, int end) {
        int minLevel = Integer.MAX_VALUE;
        int n_loc=0;
        int n_level=0;

        while(!queue.isEmpty()) {
            pos poll = queue.poll();
            if((poll.loc == end) && (poll.level<minLevel)) {
                minLevel = poll.level;
                continue;
            }

            for(int i=0;i<3;i++){
                if(i==0)
                    n_loc = poll.loc-1;
                else if(i==1)
                    n_loc = poll.loc+1;
                else
                    n_loc = poll.loc*2;

                if((0<=n_loc)&& (n_loc<=100000)) {
                    if(check[n_loc]==false) {
                        n_level = poll.level + 1;
                        queue.add(new pos(n_loc, n_level));
                        check[n_loc] = true;
                    }
                }
            }
        }
        return minLevel;
    }

    public static void main(String[] args) {
        Queue<pos> queue = new LinkedList<>();
        boolean[] check = new boolean[100001];
        Arrays.fill(check,false);
        Scanner scanner = new Scanner(System.in);

        int start = scanner.nextInt();
        int end = scanner.nextInt();

        queue.add(new pos(start, 0));
        check[start]=true;
        int result = bfs(queue,check, end);
        System.out.println(result);
    }
}

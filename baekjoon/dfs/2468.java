import java.util.*;

public class Dfs_2468 {

    static int[][]map;
    static boolean[][]visit;
    static int num=0;
    static int maxNum =Integer.MIN_VALUE;
    static int []x = {1,-1,0,0};
    static int []y = {0,0,1,-1};
    static int fieldNum =0;

    public static void dfs(int x1 ,int y1, int standard) {

        if(visit[x1][y1] !=true && map[x1][y1]>standard) {
            visit[x1][y1] = true; //방문 표시
            fieldNum++;

            for(int i=0;i<4;i++) {
                if((0<=x1+x[i] && x1+x[i]<num) && (0<=y1+y[i] && y1+y[i]<num)){
                    if(map[x1+x[i]][y1+y[i]] > standard) {
                        dfs(x1+x[i], y1+y[i], standard);
                    }
                }

            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count=0;
        int temp =0;
        int i =0;

        num = Integer.parseInt(scanner.nextLine());
        map = new int[num][num];
        visit = new boolean[num][num];
        List<Integer> result = new ArrayList<Integer>();

        for(i=0;i<num;i++) {
            String[] s = scanner.nextLine().split(" ");
            for(int j=0;j<num;j++) {
                temp = Integer.parseInt(s[j]);
                map[i][j] = temp;
                if(maxNum < temp)
                    maxNum = temp;
            }
        }

        int max =0;
        for(i=0;i<maxNum;i++) {
            for(int q=0;q<visit.length;q++) {
                Arrays.fill(visit[q], false);
            }

            for(int j=0;j<num;j++) {
                for(int k=0;k<num;k++) {
                    dfs(j, k, i);
                    if(fieldNum!=0){
                        count++;
                        fieldNum=0;
                    }
                }
            }
            max = Math.max(max, count);
            count=0;
        }
        System.out.println(max);
    }
}

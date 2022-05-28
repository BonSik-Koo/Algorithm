package baekjoon.dfs;

import java.util.Arrays;
import java.util.Scanner;

public class Dfs_1405 {

    public static int x[] = {0,0,1,-1};
    public static int y[] = {1, -1, 0, 0};
    public static double[] probability = new double[4];

    public static double answer=0.0;
    public static int maxCount =0;

    public static void bfs(boolean[][] field , int x_n , int y_n , int count, double result) {

        if(count == maxCount) {
            answer= answer+result;
            System.out.println(result);
            return;
        }
        for(int i=0;i<4;i++) {
            if(field[x_n+x[i]][y_n+y[i]]!=true && probability[i]!=0.0) {
                System.out.println("i:" + i + " for: result: " + result);
                result = result * probability[i];
                field[x_n+x[i]][y_n+y[i]]=true;
                bfs(field, x_n+x[i], y_n+y[i], count+1, result);

                //field[x_n+x[i]][y_n+y[i]]=false; ?????????????????
                //result = result / probability[i];????????????
            }
        }
    }
    public static void main(String[] args) {

        boolean[][]field = new boolean[40][40];
        double value =0.0;

        Scanner sc = new Scanner(System.in);
        maxCount = sc.nextInt();

        for(int i=0;i<4;i++) {
            value = sc.nextInt();
            probability[i] = value/100;
        }
        //System.out.println(Arrays.toString(probability));
        //Arrays.fill(field,false);
        for(int i=0;i<40;i++) {
            for(int j=0;j<40;j++) {
                field[i][j]=false;
            }
        }
        bfs(field, 20,20, 0,1);

        System.out.println("answer:" + answer);
    }
}

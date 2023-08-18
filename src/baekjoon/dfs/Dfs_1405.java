package baekjoon.dfs;

import java.util.Scanner;

public class Dfs_1405 {

    public static boolean[][] field = new boolean[40][40];
    public static int x[] = {0,0,1,-1};
    public static int y[] = {1,-1,0,0};
    public static double[] probability = new double[4];

    public static double answer=0.0;
    public static int maxCount =0;

    public static void dfs(int x_n , int y_n , int count, double result) {
        if(count == maxCount) {
            answer= answer+result;
            return;
        }
        for(int i=0;i<4;i++) {
            if(field[x_n+x[i]][y_n+y[i]]!=true && probability[i]!=0.0) {
                field[x_n][y_n]=true;
                dfs(x_n+x[i], y_n+y[i], count+1, result * probability[i]);
                field[x_n][y_n]=false;
            }
        }
    }
    public static void main(String[] args) {

        double value =0.0;
        Scanner sc = new Scanner(System.in);
        maxCount = sc.nextInt();

        for(int i=0;i<4;i++) {
            value = sc.nextInt();
            probability[i] = value/100;
        }

        dfs(20,20, 0,1);
        System.out.println(String.format("%.10f", answer));
    }
}

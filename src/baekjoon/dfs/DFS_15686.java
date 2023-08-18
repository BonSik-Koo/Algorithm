package baekjoon.dfs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFS_15686 {
    static int maxChickNum = 0;
    static int allChickNum =0;
    static class location {
        int x; int y;
        public location() {}
        public location(int x1, int y1) { this.x = x1; this.y =y1;}
//        public String toString() {
//            return "x: " + this.x + " y: " + this.y;
//        }
    }
    static List<location> house = new ArrayList<>();
    static List<location> chicken = new ArrayList<>();
    static boolean[] check;
    static int result =Integer.MAX_VALUE;

    public static void dfs(int index, int count) {

        if(count == maxChickNum) {
            int res =0;
            for(int i=0;i< house.size();i++) {
                location houseLocation = house.get(i);
                int min = Integer.MAX_VALUE;

                for(int j=0;j<chicken.size();j++) {
                    if(check[j]==true) {
                        location chickLocation = chicken.get(j);
                        min = Math.min(min, Math.abs(houseLocation.x-chickLocation.x) + Math.abs(houseLocation.y-chickLocation.y));
                    }
                }
                res+=min;
            }

            result = Math.min(res, result);
            return;
        }

        for(int i = index; i< allChickNum; i++) { //dfs하는 부분
            check[i] = true;
            dfs(i+1, count+1);
            check[i] = false;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int fieldNum = sc.nextInt();
        maxChickNum = sc.nextInt();

        for(int i=0;i<fieldNum;i++){
            for(int j=0;j<fieldNum;j++) {
                int temp = sc.nextInt();
                if(temp==1)
                    house.add(new location(i,j));
                else if(temp==2) {
                    chicken.add(new location(i, j));
                    allChickNum++;
                }
            }
        }
        check = new boolean[allChickNum];

        dfs(0,0);
        System.out.println(result);
    }
}

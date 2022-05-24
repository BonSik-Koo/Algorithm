import java.util.ArrayList;
import java.util.Scanner;

public class Dfs_2606 {

    public static int nodeNum=0;
    public static boolean[] node=null;

    public static int edgeNum=0;
    public static ArrayList<Integer> edge[]=null;

    public static int computerNum=0;

    public static void bfs(int n) {

        node[n]=true;
        for(int i=0;i<edge[n].size();i++) {
            int endNode = edge[n].get(i);
            if(node[endNode]==false) {
                computerNum++;
                bfs(endNode);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        nodeNum = sc.nextInt();
        edgeNum= sc.nextInt();

        node = new boolean[nodeNum+1];
        edge = new ArrayList[nodeNum+1];
        for(int i=1;i<=nodeNum;i++) {
            edge[i]=new ArrayList<Integer>();
            node[i] =false;
        }

        for(int i=1; i<=edgeNum; i++) {
            int n1 = sc.nextInt();
            int n2 = sc.nextInt();

            edge[n1].add(n2);
            edge[n2].add(n1);
        }
        bfs(1);

        System.out.println(computerNum);
    }
}

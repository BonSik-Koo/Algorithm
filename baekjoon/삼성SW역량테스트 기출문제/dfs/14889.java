import java.util.Arrays;
import java.util.Scanner;

public class DFS_14889 {

    static int num=0;
    static int[][] field;
    static boolean[] visit;
    static int result=Integer.MAX_VALUE;

    public static void dfs(int index, int count) { //팀 나누기를 dfs 로 구현
        if(count == num/2) {
            int re = diff_cal();
            result = Math.min(result, re);
            return;
        }

        for(int i=index;i<num;i++) {
            visit[i] = true;
            dfs(i+1, count+1);
            visit[i] = false;
        }
    }

    public static int diff_cal() {
        int result1 = 0;
        int result2 =0;

        for(int i=0;i<num;i++) {
            for(int j=i+1;j<num;j++) {
                if(visit[i]==true && visit[j]==true) { //스타트팀(자기자신은 0이니 신경x)
                    result1 +=field[i][j] + field[j][i];
                }
            }
        }

        for(int i=0;i<num;i++) {
            for(int j=i+1;j<num;j++) {
                if(visit[i]==false && visit[j]==false) { //링크팀(자기자신은 0이니 신경x)
                    result2 +=field[i][j] + field[j][i];
                }
            }
        }

        return(Math.abs(result1-result2));
    }

    public static void main(String[] args) {

        int temp=0;
        Scanner sc = new Scanner(System.in);

        num = sc.nextInt();
        field = new int[num][num];
        visit = new boolean[num];
        Arrays.fill(visit,false);

        for(int i=0;i<num;i++) {
            for(int j=0;j<num;j++) {
                temp = sc.nextInt();
                field[i][j] = temp;
            }
        }

        visit[0]=true; //스타트팀으로 고정(계산 횟수 감소)
        dfs(1,1);
        System.out.println(result);
    }
}

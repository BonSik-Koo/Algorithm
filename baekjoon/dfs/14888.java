import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DFS_14888 {

    static int num =0;
    static int[] numFiled;
    static List<Character> calMark;
    static boolean[] visitCalMark;
    static int MaxNum =Integer.MIN_VALUE;
    static int MinNum = Integer.MAX_VALUE;

    public static void dfs(int calNum , int count) {
        if(count == num-1) {
            MaxNum = Math.max(MaxNum, calNum);
            MinNum = Math.min(MinNum, calNum);
            return;
        }

        int new_calNum=0;
        for (int i = 0; i < calMark.size(); i++) {
            if(visitCalMark[i]!=true) {
                visitCalMark[i] = true;
                Character cha = calMark.get(i);
                if (cha == '+')
                    new_calNum = calNum + numFiled[count + 1];
                else if (cha == '-')
                    new_calNum = calNum - numFiled[count + 1];
                else if (cha == '*')
                    new_calNum = calNum * numFiled[count + 1];
                else { // '/'
                    if (calNum < 0) { //음수인 경우
                        int temp = Math.abs(calNum) / numFiled[count + 1];
                        new_calNum = -temp;
                    } else
                        new_calNum = calNum / numFiled[count + 1];
                }
                dfs(new_calNum, count + 1);
                visitCalMark[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        num = sc.nextInt();
        numFiled = new int[num];
        calMark = new ArrayList<>();
        visitCalMark = new boolean[num-1];

        for(int i=0;i<num;i++) {
            numFiled[i] = sc.nextInt();
        }

        int count=0;
        char[] mark = {'+', '-' , '*', '/' };
        for(int i=0;i<4;i++) {
            count = sc.nextInt();
            for(int j=0;j<count;j++) {
                calMark.add(mark[i]);
            }
        }

        dfs(numFiled[0], 0);

        System.out.println(MaxNum);
        System.out.println(MinNum);
    }
}

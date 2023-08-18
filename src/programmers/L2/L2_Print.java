package programmers.L2;

import java.util.ArrayList;

class printer {
    public int ID;
    public int pr;

    printer(int ID, int pr) {
        this.ID= ID;
        this.pr = pr;
    }
}
public class L2_Print {
    public static int solution(int[] priorities, int location) {
        int answer = 0;
        boolean bo =false;
        ArrayList<printer> qu = new ArrayList<> ();
        for(int i =0 ;i<priorities.length ;i++) {
            printer temp = new printer(i,priorities[i]);
            qu.add(temp);
        }

        while(true) {
            if(!qu.isEmpty()) {
                int x = qu.get(0).pr;
                for (int i = 1; i < qu.size(); i++) {
                    //System.out.println(qu.size());
                    int y = qu.get(i).pr;
                    if (x < y) {
                        bo = true;
                        break;
                    }
                }

                if (bo == true) {
                    printer t = qu.get(0);
                    qu.remove(0);
                    qu.add(t);
                }
                else {  //false
                    int z = qu.get(0).ID;
                    qu.remove(0);
                    answer++;
                    if (z == location)
                        break;
                }
                bo=false;
            }
        }
        return answer;
    }
    public static void main(String [] args) {
        int [] temp = {1,1,9,1,1,1};
        int x =solution(temp, 0);
        System.out.println(x);
    }
}

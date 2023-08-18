import java.util.*;

public class Dfs_2667 {

    static int[][] field;
    static boolean[][] visit;
    static int result=0;
    static int resultSum=0;
    static int num=0;
    static int[] x ={1,-1,0,0};
    static int[] y ={0,0,1,-1};

    public static void dfs(int x1, int y1) {

        if(field[x1][y1]==1 && visit[x1][y1]!=true) {
            visit[x1][y1]=true; //해당 좌표 방문 표시
            result++;

            for(int i=0;i<4;i++) {
                if ((0 <= x1 + x[i] && x1 + x[i] < num) && (0 <= y1 + y[i] && y1 + y[i] < num)) {
                    if (field[x1 + x[i]][y1 + y[i]] == 1 && visit[x1 + x[i]][y1 + y[i]] != true) {
                        dfs(x1 + x[i], y1 + y[i]);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Integer> resultArray = new ArrayList<>();

        num = Integer.parseInt(scanner.nextLine());
        field = new int[num][num];
        visit = new boolean[num][num];

        for(int i=0;i<num;i++){
            String s = scanner.nextLine();
            for(int j=0;j<num;j++) {
                field[i][j]=Character.getNumericValue(s.charAt(j));
            }
        }

        for(int i=0;i<num;i++) {
            for(int j=0;j<num;j++) {
                dfs(i, j);
                if(result>=1) {
                    resultArray.add(result);
                    result =0;
                    resultSum++;
                }
            }
        }

        System.out.println(resultSum);
        Collections.sort(resultArray);
        resultArray.stream()
                .forEach(n -> System.out.println(n));
    }
}

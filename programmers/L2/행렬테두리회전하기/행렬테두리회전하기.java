import java.util.Arrays;

/**
 * greedy 풀이
 *
 */
public class 행렬테두리회전하기 {

    static int row;
    static int col;
    public static int turn(int[][]array, int row1, int row2, int col1, int col2) {

        int [][]copy = new int[row+1][col+1];
        int min = Integer.MAX_VALUE;

        //부분 copy
        for(int i=row1;i<=row2;i++) {
            for(int j=col1;j<=col2;j++) {
                copy[i][j] = array[i][j];
            }
        }

        for(int i=row1;i<=row2;i++) {
            for(int j=col1;j<=col2;j++) {

                if((row1<i && i<=row2) && (j==col1)){
                    array[i-1][j] = copy[i][j];
                }else if((row1<=i && i<row2) && (j==col2)){
                    array[i+1][j] = copy[i][j];
                }else if((row1==i) && (col1<=j && j<col2)){
                    array[i][j+1] = copy[i][j];
                }else if((row2==i) && (col1<j && j<=col2)) {
                    array[i][j-1] = copy[i][j];
                }else
                    continue;

                min = Math.min(min, copy[i][j]);
            }
        }
        return min;
    }

    public static int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        row = rows;
        col = columns;
        int [][] array = new int[rows+1][columns+1]; //원본
        //초기값
        int count = 1;
        for(int i=1;i<=rows; i++) {
            for(int j=1;j<=columns;j++) {
                array[i][j] = count;
                count++;
            }
        }

        for(int i=0;i<queries.length;i++) {

            int row1 = queries[i][0];
            int row2 = queries[i][2];
            int col1 =queries[i][1];
            int col2 = queries[i][3];

            int min = turn(array, row1, row2, col1, col2);
            answer[i] = min;
        }

        return answer;
    }

    public static void main(String[] args) {

        int rows = 6;
        int columns = 6;
        int[][] array = {{2,2,5,4},{3,3,6,6},{5,1,6,3}};
        int[] solution = solution(rows, columns, array);

        Arrays.stream(solution).forEach(e -> System.out.print(e + " "));
    }


}

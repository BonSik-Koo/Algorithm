package hackerRank.dfs;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result5 {

    static final int[]dx ={1,-1,0,0,-1,1,-1,1};
    static final int[]dy = {0,0,1,-1,-1,-1,1,1};
    static Boolean[][]visit;
    static int n, m;
    static int count;
    static int resultCount =0;
    public static int maxRegion(List<List<Integer>> grid) {
        n = grid.size();
        m=grid.get(0).size();
        visit = new Boolean[n][m];
        for(int i=0;i<n;i++){
            Arrays.fill(visit[i], false);
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid.get(i).get(j)==1 && visit[i][j] == false){
                    count=1;
                    visit[i][j] = true;

                    dfs(i,j,grid);
                    resultCount = Math.max(resultCount, count);
                }
            }
        }
        return resultCount;
    }

    public static void dfs(int x, int y, List<List<Integer>> grid){
        for(int i=0;i<8;i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx<0 || ny<0 || nx>=n || ny>=m){
                continue;
            }
            if(grid.get(nx).get(ny)==1 && visit[nx][ny]==false){
                count+=1;
                visit[nx][ny] = true;
                dfs(nx,ny,grid);
            }
        }
    }
}

public class Connected_Cell_in_a_Grid {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        int m = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> grid = new ArrayList<>();

        IntStream.range(0, n).forEach(i -> {
            try {
                grid.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int res = Result5.maxRegion(grid);
        System.out.println(res);

//        bufferedWriter.write(String.valueOf(res));
//        bufferedWriter.newLine();

        bufferedReader.close();
//        bufferedWriter.close();
    }
}

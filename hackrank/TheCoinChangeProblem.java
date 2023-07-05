package hackerRank.dp;

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

class Result {

    /*
     * Complete the 'getWays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. LONG_INTEGER_ARRAY c
     */

    public static long getWays(int n, List<Long> c) {

        long[]dp = new long[n+1];
        Arrays.fill(dp, 0);
        dp[0] = 1; //init value

        for(Long num : c){ //탐색할 현재 숫자
            if(num > n){ //만들려는 숫자(n)보다 탐색할 숫자(num)이 더 큰 경우
                continue;
            }

            //dp start
            int num_i = num.intValue(); //어차피 위에서 한번 걸렸기 때문에 해당(num)은 int 형임.
            for(int i = num_i; i<=n; i++){
                dp[i] = dp[i] + dp[i-num_i];
            }
        }
        return dp[n];
    }
}

public class TheCoinChangeProblem {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        List<Long> c = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                .map(Long::parseLong)
                .collect(toList());

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = Result.getWays(n, c);
        System.out.println(ways);

//        bufferedWriter.write(String.valueOf(ways));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
}

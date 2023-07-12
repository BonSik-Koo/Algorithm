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


class Result4 {

    /*
     * Complete the 'maxSubarray' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts INTEGER_ARRAY arr as parameter.
     */

    public static List<Integer> maxSubarray(List<Integer> arr) {
        int[] subDp = new int[arr.size()];
        subDp[0] = arr.get(0);
        int subSumMax = arr.get(0);
        int seqSumMax = arr.get(0);

        for(int i=1;i< arr.size();i++){
            //SubArray
            subDp[i] = Math.max(arr.get(i), subDp[i-1]+ arr.get(i));
            subSumMax = Math.max(subSumMax, subDp[i]);

            //Sequence arr
            if(arr.get(i)>0){
                if(seqSumMax < 0){
                    seqSumMax = arr.get(i);
                }else{
                    seqSumMax += arr.get(i);
                }
            }else{
                if(seqSumMax < arr.get(i)){
                    seqSumMax=arr.get(i);
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        result.add(subSumMax);
        result.add(seqSumMax);
        return result;
    }

}
public class The_Maximum_Subarray {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> result = Result4.maxSubarray(arr);
                System.out.println(result.toString());

//                bufferedWriter.write(
//                        result.stream()
//                                .map(Object::toString)
//                                .collect(joining(" "))
//                                + "\n"
//                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
//        bufferedWriter.close();
    }
}

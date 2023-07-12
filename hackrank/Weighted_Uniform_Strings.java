package hackerRank.string;

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

class Result3 {
    public static List<String> weightedUniformStrings(String s, List<Integer> queries) {
        HashSet<Integer> valueSet = new HashSet<>();
        List<String> result = new ArrayList<>();

        char currentCh = s.charAt(0);
        int count = 1;
        for(int i=1; i<s.length(); i++) {
            char nextCh = s.charAt(i);
            //이전의 문자와 같은 경우
            if(currentCh == nextCh){
                count++;
                continue;
            }

            //이전의 문자와 다른 경우, 마지막 문자인 경우
            int w = currentCh - 'a' + 1;
            for(int j=1;j<=count;j++){
                valueSet.add(w*j);
            }
            currentCh = nextCh;
            count=1;
        }
        //마지막 문자열에 대해서
        int w = currentCh - 'a' + 1;
        for(int j=1;j<=count;j++){
            valueSet.add(w*j);
        }

        for(int q : queries){
            String r = (valueSet.contains(q))?"Yes":"No";
            result.add(r);
        }
        return result;
    }
}

public class Weighted_Uniform_Strings {

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = bufferedReader.readLine();

        int queriesCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> queries = IntStream.range(0, queriesCount).mapToObj(i -> {
                    try {
                        return bufferedReader.readLine().replaceAll("\\s+$", "");
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                })
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(toList());

        List<String> result = Result3.weightedUniformStrings(s, queries);
        System.out.println(result.toString());
//
//        bufferedWriter.write(
//                result.stream()
//                        .collect(joining("\n"))
//                        + "\n"
//        );

        bufferedReader.close();
//        bufferedWriter.close();
    }
}

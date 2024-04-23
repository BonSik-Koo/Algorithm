package baekjoon.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class 가르침_1062 {
    static boolean[] check;
    static String[] words;
    static int result = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        words = new String[N];

        for(int i=0; i<N; i++) {
            words[i] = br.readLine();
        }

        // a ~ z
        check = new boolean[26];
        check['a' - 'a'] = true;
        check['n' - 'a'] = true;
        check['t' - 'a'] = true;
        check['i' - 'a'] = true;
        check['c' - 'a'] = true;

        if(K==26) {
            System.out.println(N);
        } else if(K < 5) {
            System.out.println(0);
        } else {
            backTracking(0, 0, K);
            System.out.println(result);
        }
    }

    static void backTracking(int idx, int depth, int K) {
        if(depth == K-5) {
            int total = getReadableWordCnt();
            result = Math.max(result, total);
            return;
        }

        for(int i=idx; i<26; i++) {
            if(check[i]) continue;

            check[i] = true;
            backTracking(i+1, depth + 1, K);
            check[i] = false;
        }
    }

    static int getReadableWordCnt() {
        int cnt = 0;
        for(int i=0; i<words.length; i++) {
            boolean isRead = true;
            String cur = words[i];
            for(int j=0; j<cur.length(); j++) {
                if(!check[cur.charAt(j) - 'a']) {
                    isRead = false;
                    break;
                }
            }

            if(isRead) {
                cnt++;
            }
        }

        return cnt;
    }

}

package baekjoon.구현;

import java.io.*;
import java.util.*;

public class 폴더정리_22860 {
    static Map<String, Set<String>> folders = new HashMap<>();
    static Map<String, Set<String>> files = new HashMap<>();
    static int fileCnt = 0;
    static Set<String> fileKind = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for(int i=0; i<N+M; i++) {
            st = new StringTokenizer(br.readLine());
            String parent = st.nextToken();
            String child = st.nextToken();
            Boolean isFolder = Integer.parseInt(st.nextToken()) == 1;

            // 생성 초기화
            if(isFolder && !folders.containsKey(parent)) {
                folders.put(parent, new HashSet<>());
                files.put(parent, new HashSet<>());
            }
            if(isFolder && !folders.containsKey(child)) {
                folders.put(child, new HashSet<>());
                files.put(child, new HashSet<>());
            }
            if(!isFolder && !folders.containsKey(parent)) {
                folders.put(parent, new HashSet<>());
                files.put(parent, new HashSet<>());
            }

            // 입력값
            if(isFolder) {
                folders.get(parent).add(child);
            } else {
                files.get(parent).add(child);
            }
        }

        StringBuilder result = new StringBuilder();
        int Q = Integer.parseInt(br.readLine());
        for(int i=0; i<Q; i++) {
            String[] paths = br.readLine().split("/");
            fileCnt = 0;
            fileKind = new HashSet<>();

            updateFileResult(paths[paths.length-1]);
            result.append(fileKind.size() + " " + fileCnt + "\n");
        }

        System.out.println(result.toString());
    }

    static void updateFileResult(String folder) {
        // 자기 자신
        fileCnt += files.get(folder).size();
        fileKind.addAll(files.get(folder));

        // 자식 폴더
        for(String child : folders.get(folder)) {
            updateFileResult(child);
        }
    }

}

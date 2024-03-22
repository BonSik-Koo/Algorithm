package programmers.L3.순위검색;
import java.util.*;

class Solution {
    Map<String, List<Integer>> map = new HashMap<>();

    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];

        // 키워드 조합 구하기
        for(String s : info) {
            initDfs(s.split(" "), "", 0);
        }

        // 점수 정렬 (이분 탐색을 위해)
        for(String key : map.keySet()) {
            Collections.sort(map.get(key));
        }

        // 조건 충족 인원 찾기
        for(int i=0; i<query.length; i++) {
            answer[i] = findCount(query[i]);
        }

        return answer;
    }

    private int findCount(String query) {
        String[] arr = query.split(" and ");
        int score = Integer.parseInt(arr[3].split(" ")[1]);

        String key = arr[0] + arr[1] + arr[2] + arr[3].split(" ")[0];

        if(!map.containsKey(key)) return 0;

        // 최소 점수 인덱스 찾기
        List<Integer> scoreList = map.get(key);
        int minIdx = binarySearch(scoreList, score);

        return scoreList.size() - minIdx;
    }

    private int binarySearch(List<Integer> score, int tScore) {
        int low = 0;
        int high = score.size();

        while(low < high) {
            int mid = (low + high) / 2;
            if(tScore <= score.get(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }

        }

        return low;
    }

    private void initDfs(String[] info, String str, int depth) {
        if(depth == 4) {
            int score = Integer.parseInt(info[depth]);
            if(map.containsKey(str)) {
                map.get(str).add(score);
            } else {
                List<Integer> scores = new ArrayList<>();
                scores.add(score);
                map.put(str, scores);
            }
            return;
        }

        initDfs(info, str + info[depth], depth+1);
        initDfs(info, str + "-", depth+1);
    }

}
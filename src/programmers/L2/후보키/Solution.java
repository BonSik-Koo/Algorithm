package programmers.L2.후보키;

import java.util.*;

class Solution {
    private int[] columns;

    public int solution(String[][] relation) {
        // 모든 키 조합 구하기
        initBitmasking(relation);

        // 모든 키 조합에 대한 유일성 점검
        List<List<Integer>> result = new ArrayList<>();
        for(int i=0; i<columns.length; i++) {
            // 선택된 키의 컬럼 인덱스 찾기
            List<Integer> keys = new ArrayList<>();
            for(int j=0; j<relation[0].length; j++) {
                if((columns[i] & (1<<j)) != 0) {
                    keys.add(j);
                }
            }

            // 해당 키가 유일성을 만족하는지 검증
            if(!check(relation, keys)) {
                continue;
            }

            // 최소성 점검 (중복 컬럼)
            boolean ch = true;
            for(List<Integer> temp : result) {
                // result 배열에 작은 키 인덱스 조합부터 들어가므로 keys를 기준으로 검증하기
                if(keys.containsAll(temp)) {
                    ch = false;
                    break;
                }
            }
            if(ch) {
                result.add(keys);
            }
        }


        return result.size();
    }

    private boolean check(String[][] relation, List<Integer> keys) {
        List<String> temp = new ArrayList<>();
        for(int i=0; i<relation.length; i++) {
            StringBuilder sb = new StringBuilder();
            for(int key : keys) {
                sb.append(relation[i][key]);
            }

            if(!temp.contains(sb.toString())) {
                temp.add(sb.toString());
            } else {
                return false;
            }
        }
        return true;
    }

    private void initBitmasking(String[][] relation) {
        int size = relation[0].length;

        columns = new int[(1<<size) - 1];
        int idx = 0;
        for(int i=1; i<(1<<size); i++) {
            columns[idx++] = i;
        }
    }
}
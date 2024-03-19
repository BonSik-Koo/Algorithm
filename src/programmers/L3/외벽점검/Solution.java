package programmers.L3.외벽점검;

class Solution {
    private int weakLength;
    private int[] appendWeak;
    private int[] dist;
    private int result = Integer.MAX_VALUE;

    public int solution(int n, int[] weak, int[] dist) {
        weakLength = weak.length;
        this.dist = dist;
        this.appendWeak = new int[weak.length*2-1];

        for(int i=0; i<weak.length; i++) {
            this.appendWeak[i] = weak[i];
        }
        for(int i=0; i<weak.length-1; i++) {
            this.appendWeak[i+weak.length] = weak[i] + n;
        }

        makeDistCases(new boolean[dist.length], new int[dist.length], 0);
        return (result == Integer.MAX_VALUE) ? -1 : result;
    }

    private void makeDistCases(boolean[] visit, int[] distCase, int idx) {
        // 조합을 완성한 경우
        if(idx == dist.length) {
            for(int i=0; i<weakLength; i++) {
                check(i, distCase);
            }
            return;
        }

        for(int i=0; i<dist.length; i++) {
            if(visit[i]) continue;

            visit[i] = true;
            distCase[idx] = dist[i];
            makeDistCases(visit, distCase, idx+1);
            visit[i] = false;
        }
    }

    private void check(int start, int[] distCase) {
        int distIdx = 0;
        int cur = start;
        while(cur < start + weakLength && distIdx < dist.length) {
            int cover = appendWeak[cur] + distCase[distIdx++];
            while(cur < start + weakLength && appendWeak[cur] <= cover) {
                cur++;
            }
        }

        // 모두 점검한 경우
        if(cur == start + weakLength) {
            result = Math.min(result, distIdx);
        }
    }

}
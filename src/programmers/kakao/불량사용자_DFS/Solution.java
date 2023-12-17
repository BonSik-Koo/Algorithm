package programmers.kakao.불량사용자_DFS;

import java.util.*;

class Solution {
    static boolean[] visit;
    static Set<String> ids;

    public int solution(String[] user_id, String[] banned_id) {
        visit = new boolean[user_id.length];
        ids = new HashSet<>();

        for(int i=0; i<banned_id.length; i++){
            String replaceStr = banned_id[i].replace("*", ".");
            banned_id[i] = replaceStr;
        }

        dfs(user_id, banned_id, "", 0);

        return ids.size();
    }

    public void dfs(String[] user_id, String[] bannedId, String result, int depth) {
        if(depth == bannedId.length) {
            String[] userIds = result.split(" ");
            Arrays.sort(userIds);

            String ans = "";
            for(String userId : userIds){
                ans += userId;
            }
            ids.add(ans);
            return;
        }

        for(int i=0; i<user_id.length; i++){
            if(visit[i] || !user_id[i].matches(bannedId[depth])) continue;

            visit[i] = true;
            dfs(user_id, bannedId, result + " " + user_id[i], depth + 1);
            visit[i] = false;
        }

    }
}
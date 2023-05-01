import java.util.*;

class Solution {
    public class Skill{
        Map<Character, Integer> skillMap;
        
        public Skill(String skill){
            int index = 0;
            skillMap = new HashMap<>();
            for(int i=0;i<skill.length();i++)
                this.skillMap.put(skill.charAt(i), index++);
        }
        
        public Boolean isValid(String str){
            int seq =0;
            for(int i=0;i<str.length();i++){
                Integer index = this.skillMap.get(str.charAt(i));
                if(index == null) //선행 스킬에 없는 스킬인 경우
                    continue;
            
                if(seq!=index)
                    return false;
                seq++;
            }
            return true;
        }
    }
    
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        Skill skillMap = new Skill(skill);
        for(String str : skill_trees){
            if(skillMap.isValid(str))
                answer++;
        }
        
        return answer;
    }
}

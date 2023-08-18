import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * -풀이방법-
 *
 */
public class 단어변환 {

    public static class zip {
        String word;
        int count;
        ArrayList<Integer> useIndex = new ArrayList<>(); //사용한 words 의 인덱스 위치

        public zip(String word, int count, ArrayList<Integer> useIndex) {
            this.word = word;
            this.count = count;
            this.useIndex = useIndex;
        }
    }

    public static int bfs(String begin, String target, String[] words) {

        int result =0;
        Queue<zip> queue = new LinkedList<>();
        queue.add(new zip(begin, 0, new ArrayList<>()));

        while(!queue.isEmpty()) {
            zip poll = queue.poll();

            if(poll.word.equals(target)) { //정상 종료 조건
                result = poll.count;
                break;
            }

            for(int i=0;i< words.length;i++) {

                if(poll.useIndex.contains(i)) //이미 사용한 인덱스 인경우
                    continue;

                String currentWord = words[i];
                int num =0;
                for(int j=0;j<currentWord.length();j++) {
                    if(poll.word.charAt(j) == currentWord.charAt(j))
                        num++;
                }

                if(num==currentWord.length()-1) { //단어 변환이 가능한 경우

                    poll.useIndex.add(i);
                    queue.add(new zip(currentWord, poll.count +1 , new ArrayList<>(poll.useIndex)));
                }
            }

        }
        return result;
    }

    public static int solution(String begin, String target, String[] words) {
        int answer = 0;
        answer = bfs(begin,target, words);
        return answer;
    }

    //test 메인 함수수
   public static void main(String[] args) {
        String [] word = {"hot", "dot", "dog", "lot", "log"};
        int solution = solution("hit", "cog", word);

        System.out.println(solution);

    }


}

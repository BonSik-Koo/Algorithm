import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주의 할점 : 처음에 넣을때 같은 플러그가 중복될수도 있으니 중복처리해야된다.
 */
public class G_1700 {

    public static void main(String[]args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Integer> list = new ArrayList<>();
        List<Integer> currentList = new ArrayList<>();

        st = new StringTokenizer(bf.readLine());
        int count =0;
        for(int i=0;i<K;i++) {
            int product = Integer.parseInt(st.nextToken());
            if(count<N) {
                if(!currentList.contains(product)) {
                    currentList.add(product);
                    count++;
                    continue;
                }
            }
            list.add(product);
        }

        //실제 로직
        int result =0;
        while (!list.isEmpty()) {
            Integer productNum = list.get(0);

            boolean contains = currentList.contains(productNum);
            if(contains == true) {
                list.remove(0);
                continue;
            }

            //멀티탭안에 있는 리스트들은 중복 반복..
            int popIndex =0;
            int pop =0; //뽑을 값
            for(int i=0;i<N;i++) {
                Integer temp = currentList.get(i);
                Integer index = list.indexOf(temp);

                if(index == -1) {
                    pop = i;
                    break;
                }

                if(popIndex < index) { //뽑을플러그 선정
                    popIndex = index;
                    pop = i;
                }
            }

            currentList.remove(pop); //멀티탭에서 한개 추출
            currentList.add(productNum); //멀티탭에 추가
            list.remove(0);
            result++;
        }
        System.out.println(result);
    }

}

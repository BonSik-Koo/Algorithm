import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 중요!)
 * - 가방수와 보석수를 각각 모든 경우를 따지는것은 "시간초과"가 난다! (탐색한 보석은 탐색하지 않아야된다)
 * - result 자료형 크기 생각하기!!!
 */
public class G_1202 {

    public static class Jewel implements Comparable<Jewel>{
        int weight; int price;
        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
        @Override
        public int compareTo(Jewel o) { //우선 순위큐에 대해서 가격 내림차순 정렬
            return o.price - this.price;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int jewelNum = Integer.parseInt(st.nextToken());
        int bagNum = Integer.parseInt(st.nextToken());
        List<Jewel> jewelList = new ArrayList<>();
        List<Integer> bagList = new ArrayList<>();
        long result =0;

        for(int i=0;i<jewelNum;i++) {
            st = new StringTokenizer(bf.readLine());
            jewelList.add(new Jewel(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        for(int i=0;i<bagNum;i++) {
            bagList.add(Integer.parseInt(bf.readLine()));
        }
        Collections.sort(bagList); //가방 무게 오름차순 정렬
        Collections.sort(jewelList, new Comparator<Jewel>() {
            @Override
            public int compare(Jewel o1, Jewel o2) {
                if(o1.weight == o2.weight)
                    return o2.price - o1.price;
                return o1.weight - o2.weight;
            }
        }); //보석 무게 오름차순 , 같은 무게는 가격으로 내림

        Queue<Jewel> queue = new PriorityQueue<>();
        for(int i=0,j=0;i<bagNum;i++) {
            Integer bagWeight = bagList.get(i);
            while (j<jewelNum && bagWeight>=jewelList.get(j).weight) {//가방에 들어가는 경우
                queue.offer(jewelList.get(j++));
            }

            if(!queue.isEmpty()){
                result+=queue.poll().price;
            }
            /**
             * - 정렬된 배낭 크기에 들어갈수있는 보석들을 모두 큐에 넣는다.
             * - 큐에 들어간 보석은 값으로 내림차순 정렬되어있기 때문에 맨위에 있는것이 가장 값이 큰 보석
             * - 배낭크기로 오름차순으로 정렬되어있고 보석도 크기로 오름차순 정렬된 것에 대해서 큐에 넣기 때문에 하나의 배낭에 보석이 들어가면
             *   큐에 존재하는남은 보석들은 다음 배낭의 크기에 자동으로 다 들어가진다.!!
             */
        }
        System.out.println(result);
    }
}

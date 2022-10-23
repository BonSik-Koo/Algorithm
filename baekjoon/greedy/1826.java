import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class G_1826 {

    static class Gas implements Comparable<Gas>{
        int distance; int volume;
        public Gas(int distance, int volume) {
            this.distance = distance;
            this.volume = volume;
        }
        @Override
        public int compareTo(Gas o) {
            if(this.volume == o.volume)
                return this.distance - o.distance;
            return o.volume - this.volume;
        }
    }
    public static void main(String[] args) throws IOException {
        List<Gas> list = new ArrayList<>();
        BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine()); //주유소 개수
        StringTokenizer st =null;
        int totalDistance =0 , possibleDistance =0;
        int result =0;

        for(int i=0;i<num;i++) {
            st = new StringTokenizer(bf.readLine());
            list.add(new Gas(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        st = new StringTokenizer(bf.readLine());
        totalDistance = Integer.parseInt(st.nextToken());
        possibleDistance = Integer.parseInt(st.nextToken());
        Collections.sort(list);

        List<Gas> wait = new ArrayList<>();
        for(int i=0;i<num;i++) {
            Gas gas = list.get(i);
            if(possibleDistance >= gas.distance) { //이동 가능한 경우
                possibleDistance+=gas.volume;
                result++;

                if(!wait.isEmpty()) {
                    Collections.sort(wait, new Comparator<Gas>() {
                        @Override
                        public int compare(Gas o1, Gas o2) {
                            if(o1.distance == o2.distance)
                                return o2.volume - o1.volume;
                            return o1.distance - o2.distance;
                        }
                    });
                    Iterator<Gas> iterator = wait.iterator();
                    while (iterator.hasNext()){
                        Gas waitGas= iterator.next();
                        if(possibleDistance>= waitGas.distance) {
                            possibleDistance += waitGas.volume;
                            result++;
                            iterator.remove();
                        }else
                            break;
                        if(possibleDistance >=totalDistance)
                            break;
                    }
                }
            }else {
                wait.add(new Gas(gas.distance, gas.volume));
            }
            if(possibleDistance >=totalDistance)
                break;
        }
        if(possibleDistance< totalDistance)
            result = -1;
        System.out.println(result);
    }
}

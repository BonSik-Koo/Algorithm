package L2;
import java.util.HashMap;
import java.util.Map;

class Node {
    Map<Character, Node> node ;
    boolean check;
    Node() {
        this.node =new HashMap<Character, Node> ();
        this.check = false;
    }
}
public class Telephone_List {
    public static boolean solution(String[] phone_book) {
        boolean answer = true;

        Node root = new Node();
        for(int i=0;i<phone_book.length; i++ ) {
            Node n =root;

            for(int j=0;j<phone_book[i].length(); j++) {
                if( n.node.get(phone_book[i].charAt(j)) == null) {
                    n.node.put(phone_book[i].charAt(j),new Node());
                    n = n.node.get(phone_book[i].charAt(j));
                }
                else {
                    n = n.node.get(phone_book[i].charAt(j));
                    if(n.check==true || j==phone_book[i].length()-1 )
                        answer=false;
                }
            }
            n.check=true;
        }
        return answer;
    }


    public static void main(String[] args) {
        String [] x = new String[] {"119", "97674223", "1195524421"};
        boolean temp = solution(x);
        System.out.println(temp);
    }


}

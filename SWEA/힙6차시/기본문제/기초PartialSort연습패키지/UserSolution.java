package SWExpertAcademyProblem.힙6차시.기본문제.기초PartialSort연습패키지;

public class UserSolution {

    //힙 구현
    public class my_heap {

        public void maxHeapify(Node array[], int length, int i) {
            int parent = i;
            int leftChild = i * 2 + 1;
            int rightChild = i * 2 + 2;

            if (leftChild < length && array[parent].income > array[leftChild].income) { // 왼쪽 자식 값이 부모 값보다 클 경우
                parent = leftChild; //부모 index에 왼쪽 자식 index 할당
            }else if(leftChild < length && array[parent].income == array[leftChild].income){
                if(array[parent].uId < array[leftChild].uId)
                    parent = leftChild; //부모 index에 왼쪽 자식 index 할당
            }

            if (rightChild < length && array[parent].income > array[rightChild].income) { // 오른쪽 자식 값이 부모 값보다 클 경우
                parent = rightChild; //부모 index에 오른쪽 자식 index 할당
            }else if( rightChild < length && array[parent].income == array[rightChild].income){
                if(array[parent].uId < array[rightChild].uId)
                    parent = rightChild; //부모 index에 왼쪽 자식 index 할당
            }

            if (i != parent) {
                swap(array, parent, i);
                maxHeapify(array, length, parent);
            }
        }

        public void heapSort(Node[] array) {
            //int length = array.length - 1; /* index 이므로 -1 해야 함 */
            int length = count-1;

	    /* 주어진 데이터로 max heap을 만든다. Root 노드 방향으로 거슬러 올라감
	                마지막 노드의 부모 노드에서부터 시작 */
            for (int i = length / 2; i >= 0; i--) {
                maxHeapify(array, length, i);
            }

            for (int i = length; i > 0; i--) { /* 원소가 하나 남을 때 까지 반복 */
                swap(array, 0, i); /* 힙에서 최대값(루트)을 가장 마지막 값과 바꾼다. for문을 돌면서 배열 뒤에서부터 차곡차곡 오름차순으로 쌓임 */
                maxHeapify(array, i, 0); /* Root 노드 heapify */
            }
        }

        public void swap(Node[] array, int i, int k) {
            Node temp = new Node(array[i].uId, array[i].income);
            array[i] = array[k];
            array[k] = temp;
        }
    }

    static class Node {
        int uId, income;
        public Node(int uId, int income) {
            this.uId = uId;
            this.income = income;
        }
    }
    int MAX_INPUT = 100000;
    Node []array;
    int count ;
    my_heap heap;

    public void init() {
        heap = new my_heap();
        array = new Node[MAX_INPUT];
        count = 0;
    }

    public void addUser(int uID, int income) {

        Node node = new Node(uID, income);
        array[count++] = node;
    }

    int getTop10(int[] result) {

        heap.heapSort(array);

        int num =count;
        if(count>=10)
            num = 10;

        for(int i=0;i<num;i++){
            result[i] = array[i].uId;
        }
        return num;
    }
}
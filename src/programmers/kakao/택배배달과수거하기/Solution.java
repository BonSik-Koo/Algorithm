package programmers.kakao.택배배달과수거하기;

class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        long answer = 0;
        int index1 = n - 1;
        int index2 = n - 1;

        while (!(index1 < 0 && index2 < 0)) {
            //배달, 픽업이 사이클이 끝나고 현재 지점에 남은 개수가 0일 수 있으니, shift 처리
            while (index1 >= 0) {
                if (deliveries[index1] != 0) {
                    break;
                }
                index1--;
            }
            while (index2 >= 0) {
                if (pickups[index2] != 0) {
                    break;
                }
                index2--;
            }

            int deliveryAmount = cap;
            int pickupAmount = cap;
            int deliveryDis = index1 + 1;
            int pickupDis = index2 + 1;

            //끝에서 부터 배달하기
            while (index1 >= 0) {
                if (deliveries[index1] <= deliveryAmount) {
                    deliveryAmount -= deliveries[index1];
                    deliveries[index1] = 0;
                    index1--;
                } else {
                    deliveries[index1] -= deliveryAmount;
                    break;
                }
            }

            //끝에서 부터 픽업하기
            while (index2 >= 0) {
                if (pickups[index2] <= pickupAmount) {
                    pickupAmount -= pickups[index2];
                    pickups[index2] = 0;
                    index2--;
                } else {
                    pickups[index2] -= pickupAmount;
                    break;
                }
            }

            //배달, 픽업 거리 중 큰 값을 추가
            answer += (Math.max(deliveryDis, pickupDis) * 2);
        }

        return answer;
    }
}
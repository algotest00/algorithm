import java.util.*;

/**
* SET에서 처리하게해서 O(N^2)보다 작게처리리
* 우선순위
* 1. 2코인으로 라운드 두번 통과가능한경우 : 뽑은카드 2장이 페어가 모두 cardSet에 들어있을경우.
* 2. 코인이 존재할경우 : 코인사용으로 라운드 통과
* 3. 코인이 없을경우 cardSet에서 사용.
* <canUseCardSet 역할>
 * - 이후 라운드에서 뽑을 카드와의 조합을 위해 임시로 보관해두는 카드 저장소.
 * - 현재 페어는 안 되지만 추후 코인 여유 있을 때 활용 가능하도록 유지함.
* <haveToIncreaseRound역할>
 * 카드가 없을경우에도 round는 시작할 수 있음. 그러므로 for문을 초과하는경우에 라운드 +1 증가
*/
// 테케없이 나온다면 코테를 친다면 자신있게 틀릴 수 있음.
class Solution {
    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int cardCount = n/3;
        Set<Integer> cardSet = new HashSet();
        Set<Integer> canUseCardSet = new HashSet();
        //1. cardCount만큼 카드뽑기
        for (int i = 0; i < cardCount; i++) {
            int num = cards[i];
            cardSet.add(num);
        }

        // 라운드 시작
        int round = 0;
        boolean haveToIncreaseRound = false; 

        for (int i = n/3; i < cards.length; i+=2) {
            boolean canPass = false; //라운드통과 여부    
            haveToIncreaseRound = false;
            round++; //다음라운드로

            //라운드별로 카드뽑기
            int card1 = cards[i];
            int card2 = cards[i+1];
            int pairCard1 = n + 1 - card1;
            int pairCard2 = n + 1 - card2;

            //1. 뽑은카드 1장과 들고있는 카드 1장 비교
            if(cardSet.contains(pairCard1)) {
                if(coin > 0) {
                    coin--;
                    cardSet.remove(pairCard1);
                    canPass = true;
                    
                }
            } else {
                canUseCardSet.add(card1);
            }
            
            if(cardSet.contains(pairCard2)) {
                if(canPass && coin > 0) {
                    coin--;
                    cardSet.add(card2);
                }
                if(!canPass && coin > 0){
                    coin--;
                    cardSet.remove(pairCard2);
                    canPass = true;
                }
            }else {
              canUseCardSet.add(card2);
            }

            //2. 뽑은카드 2장 비교
            if(!canPass && card1 + card2 == n + 1 && coin >= 2) {
                canPass = true;
                coin -= 2;
                haveToIncreaseRound = true;
                continue;
            }

            //3. 여기까지왔는데, 없는경우. 내 카드 뭉치에서 꺼내기 , 사용가능한 카드뭉치에서 꺼내기로 또 비교
            if(!canPass) {
                int spentAmount = canPassUsingCardSet(cardSet,canUseCardSet, n);    

                if(spentAmount != -99 && coin - spentAmount >= 0) {
                    coin -= spentAmount;
                    canPass = true;
                }         
            }

            if(!canPass) break;
            haveToIncreaseRound = true;
        }
                                
        return haveToIncreaseRound ? round+1 : round;
    }
 
        
    public int canPassUsingCardSet(Set<Integer> cardSet, Set<Integer> canUseCardSet, int n) {
        for (int card : cardSet) {
            int pairCard =  n + 1 - card;
            if (cardSet.contains(pairCard)) {
                cardSet.remove(card);
                cardSet.remove(pairCard);
                return 0;    
            }
            
            if (canUseCardSet.contains(pairCard)) {
                cardSet.remove(card);
                canUseCardSet.remove(pairCard);
                return 1;
            }
        }
        
        for (int card : canUseCardSet) {
            int pairCard =  n + 1 - card;
            if (cardSet.contains(pairCard)) {
                canUseCardSet.remove(card);
                cardSet.remove(pairCard);
                return 1;    
            }
            
            if (canUseCardSet.contains(pairCard)) {
                canUseCardSet.remove(card);
                canUseCardSet.remove(pairCard);
                return 2;
            }
        }
        
        return -99;
    }
}
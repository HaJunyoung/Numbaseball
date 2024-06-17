import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int strike;
    static int ball;
    static int count;

    static void resetGame(){
        strike = 0;
        ball = 0;
        count = 1;
    }

    static int[] makeNum(){
        int[] comNum = new int[] {
                (int)(Math.random() * 9) + 1,
                (int)(Math.random() * 9) + 1,
                (int)(Math.random() * 9) + 1,
        };
        return comNum;
    }
    static int[] userNum(){
        Scanner input = new Scanner(System.in);
        int[] myNum = new int[3];

        //사용자 숫자 입력
        for (int i = 0; i < 3; i++) {
            int tempNum = input.nextInt();
            if(tempNum < 1 || tempNum > 9) {
                System.out.println("입력값을 1~9까지로 입력해주세요");
                i--;
            }
            else{
                input.nextLine();
                myNum[i] = tempNum;
            }
        }
        return myNum;
    }
    static void compareNum(int[] comNum, int[] myNum){
        for (int i = 0 ; i<comNum.length ; i++){
            for(int a=0; a<myNum.length ; a++){
                if(comNum[i] == myNum[a] && i == a) {
                    //자리 확인
                    strike++;
                }else if(comNum[i] == myNum[a] && i != a) {
                    //같은 숫자만 있으면 볼
                    ball++;
                }
            }
        }
    }

    static void countCheck(int[] myNum){
        System.out.print(count + "번째 시도 : ");
        for (int x : myNum) {
            //내숫자 확인
            System.out.print(x);
        }
        System.out.println();
        System.out.println(ball + "B" + strike + "S");
        count ++;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Strike , ball , count 초기화
        resetGame();

        // 컴퓨터 랜덤 3자리 만듦
        int[] comNum = makeNum();
        System.out.println("정답 : " + Arrays.toString(comNum));
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");

        while (true) {

            //사용자 숫자 입력
            int[] myNum= userNum();

            // 검증
            compareNum(comNum,myNum);

            // 출력
            countCheck(myNum);


            // 종료 조건
            if (strike == 3) {
                System.out.println(count + "번만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            }

        }
    }
}

import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        int[] myNum =  new int[3];
        int[] comNum = new int[] {
                (int)(Math.random() * 9) + 1,
                (int)(Math.random() * 9) + 1,
                (int)(Math.random() * 9) + 1,
        };

        System.out.println("정답 : " + Arrays.toString(comNum));

        int strike = 0;
        int ball = 0;
        int count = 1;
        Scanner input = new Scanner(System.in);

        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");
        while (true) {
            //사용자 숫자 입력
            for (int i = 0; i < comNum.length; i++) {
                int tempNum;
                do {
                    tempNum = input.nextInt();
                    input.nextLine();
                    if (tempNum > 9 || tempNum < 1) {
                        System.out.println("입력값을 1~9까지로 입력해주세요");
                    }
                } while (tempNum >= 10);
                myNum[i] = tempNum;
            }

            // 검증
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

            // 출력
            String newMyNum = Arrays.toString(myNum);
            System.out.print(count + "번째 시도 : ");
            for (int x : myNum) {
                //내숫자 확인
                System.out.print(x);
            }
            System.out.println();
            System.out.println(ball + "B" + strike + "S");
            count ++;

            // 종료 조건
            if (strike == 3) {
                System.out.println(count + "번만에 맞히셨습니다.");
                System.out.println("게임을 종료합니다.");
                break;
            }

            //초기화
            strike = 0;
            ball = 0;
        }
    }
}

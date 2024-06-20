import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Scanner;


public class Game {

    public static final int STRIKE_NUMBER = 3;

    private final Scanner input;
    private final Player computer;
    private final Player user;
    private int strike, ball;
    private int attempts = 1;
    private boolean onPlay = true;

    public Game() {
        this.computer = new Player(true);
        this.user = new Player(false);
        this.input = new Scanner(System.in);
    }

    public void startGame() {
        //정답 출력 코드
        //System.out.println("정답: " + convertArrayToString(computer.getNumbers()));
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");
        while (onPlay) {
            user.setNumbers(getUserNumbers());
            checkNumbers();
            printResult();
            if (strike == STRIKE_NUMBER) {
                endGame();
                continue;
            }
            attempts++;
            resetCounts();
        }
    }

    private void checkNumbers() {
        for (int i = 0; i < STRIKE_NUMBER; i++) {
            for (int j = 0; j < STRIKE_NUMBER; j++) {
                if (computer.getNumbers()[i] == user.getNumbers()[j]) {
                    if (i == j)
                        strike++;
                    else
                        ball++;
                }
            }
        }
    }

    private void endGame() {
        System.out.println(attempts + "번만에 맞히셨습니다.");
        System.out.println("게임을 종료합니다.");
        input.close();
        onPlay = false;

    }

    private void resetCounts() {
        strike = 0;
        ball = 0;
    }

    private void printResult() {
        if (strike == STRIKE_NUMBER) {
            System.out.println(strike + "S");
        } else if (ball == STRIKE_NUMBER) {
            System.out.println(ball + "B");
        } else {
            System.out.println(ball + "B" + strike + "S");
        }
    }

    //정답을 출력시킬때만 사용
    private String convertArrayToString(int[] numbers) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int number : numbers)
            stringBuilder.append(number);
        return stringBuilder.toString();
    }


    private int[] getUserNumbers() {
        int[] numbers = new int[STRIKE_NUMBER];
        Set<Integer> checkSame = new LinkedHashSet<>();

        while (true) {
            System.out.print(attempts + "번째 시도: ");
            String[] userNumber = input.nextLine().split("");

            if (userNumber.length != STRIKE_NUMBER) {
                System.out.println("숫자를 " + STRIKE_NUMBER + "개만 입력해주세요");
                continue;
            }

            boolean isValid = true;
            for (String chNum : userNumber) {
                if (!chNum.matches("\\d")) {
                    System.out.println("숫자만 입력해주세요.");
                    isValid = false;
                    break;
                }

                int num = Integer.parseInt(chNum);
                if (!checkSame.add(num)) {
                    System.out.println("중복된 숫자가 있습니다. 다시 입력해주세요");
                    isValid = false;
                    break;
                }
            }

            if (isValid && checkSame.size() == STRIKE_NUMBER) {
                int i = 0;
                for (int num : checkSame) {
                    numbers[i++] = num;
                }
                return numbers;
            } else {
                checkSame.clear();
            }
        }
    }


}
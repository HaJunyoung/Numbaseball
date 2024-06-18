import java.util.Arrays;
import java.util.Scanner;

public class Game {

    public static final int STRIKE_NUMBER = 3;
    private static final int MAX_NUMBER = 9;
    private static final int MIN_NUMBER = 1;

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
        System.out.println("정답: " + convertArrayToInt(computer.getNumbers()));
        System.out.println("컴퓨터가 숫자를 생성하였습니다. 답을 맞춰보세요!");
        while (onPlay) {
            user.setNumbers(getUserNumbers());
            checkNumbers();
            if (strike == STRIKE_NUMBER) {
                endGame();
                continue;
            }
            printResult();
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
        int userNum = convertArrayToInt(user.getNumbers());
        System.out.println(attempts + "번째 시도: " + userNum);
        System.out.println(ball + "B" + strike + "S");
    }

    private int convertArrayToInt(int[] numbers) {
        int result = 0;
        for (int number : numbers)
            result = result * 10 + number;
        return result;
    }

    private int[] getUserNumbers() {
        int[] userNumbers = new int[STRIKE_NUMBER];
        for(int i = 0; i < STRIKE_NUMBER; i++) {
            int tempNum;
            do {
                tempNum = input.nextInt();
                input.nextLine();
                if (tempNum > MAX_NUMBER || tempNum < MIN_NUMBER) {
                    System.out.println("입력값을 1~9까지로 입력해주세요.");
                }
            } while (tempNum > MAX_NUMBER || tempNum < MIN_NUMBER);
            userNumbers[i] = tempNum;
        }
        return userNumbers;
    }
}

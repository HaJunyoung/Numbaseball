import java.util.LinkedHashSet;

public class Player {

    private int[] numbers;

    public Player(boolean checkComputer) {
        this.numbers = new int[Game.STRIKE_NUMBER];
        if (checkComputer) {
            computerNumberCreate();
        }
    }

    private void computerNumberCreate() {
        LinkedHashSet<Integer> comNum = new LinkedHashSet<>();
        while (comNum.size() < Game.STRIKE_NUMBER) {
            int tempNum = (int)(Math.random() * 10);
            comNum.add(tempNum);
        }
        int i = 0;
        for (Integer num : comNum)
            numbers[i++] = num;
    }

    public void setNumbers(int[] numbers) {
        this.numbers = numbers;
    }

    public int[] getNumbers() {
        return numbers;
    }
}

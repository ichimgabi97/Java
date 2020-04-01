import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Player implements Runnable {

    ArrayList<Integer> numbers = new ArrayList<>();
    Board board = new Board();
    String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(Board board){
        this.board = board;
    }

    private void addNumber(int number){
        numbers.add(number);
        if (playerWon()){
            System.out.println(name);
            board.setFinish(true);
        }else {
            if (board.drawCondition()){
                System.out.println("Draw");
            }
        }
    }

    public boolean playerWon(){
        int[] winningList = new int[board.winningLengthList];
        for (int i = 0; i < numbers.size() - 1; i++){
            for (int j = i + 1; j < numbers.size(); j++) {
                winningList[0] = numbers.get(i);
                winningList[1] = numbers.get(j);
                int position = 2;
                for (int k = 0; k < numbers.size(); k++) {
                    if (position == board.winningLengthList) {
                        System.out.println(name + ": " + Arrays.toString(winningList));
                        return true;
                    }
                    if (numbers.get(k) == winningList[position - 1] + winningList[1] - winningList[0]) {
                        winningList[position] = numbers.get(k);
                        position++;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void run() {
        while (!board.drawCondition() && !board.getFinish()){
            addNumber(board.extractRandomToken());
            try {
                sleep((int) (Math.random() * 100));
            } catch (InterruptedException e){
                System.err.println(e);
            }
        }
    }
}

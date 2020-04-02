import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Arrays;

import static java.lang.Thread.sleep;

public class Player implements Runnable {

    ArrayList<Integer> numbers = new ArrayList<>();
    Board board = new Board();
    String name;
    int winningLength;
    int points;

    public void setName(String name) {
        this.name = name;
    }

    public void setBoard(Board board){
        this.board = board;
        this.winningLength = board.winningLengthList;
    }

    private void addNumber(int number){
        if (number != 0){
            numbers.add(number);
            if (playerWon()){
                this.points = board.getNumberOfTokens();
                System.out.println(name + ": " + points);
                board.setHasPlayerWon(true);
                board.setFinish(true);
            }else {
                if (board.drawCondition()){
                    System.out.println("Draw");
                }
            }
        }else{                                   // daca token.getValue() == 0  =>  token-ul are orice valoare
            winningLength --;
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
                    if (position == winningLength) {
                        System.out.println(name + ": " + Arrays.toString(winningList));
                        return true;
                    }
                    if (numbers.get(k) == winningList[position - 1] + winningList[1] - winningList[0]) {
                        points = position;
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
                sleep((int) (Math.random() * 200));
            } catch (InterruptedException e){
                System.err.println(e);
            }
        }
        if (!board.hasPlayerWon){
            System.out.println(name + ": " + points);
        }
    }
}

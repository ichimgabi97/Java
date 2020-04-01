import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Board {

    ArrayList<Token> tokens = new ArrayList<>();
    ArrayList<Boolean> tokensUsed = new ArrayList<>();
    int numberOfTokens;
    int maxValueForAToken;
    int winningLengthList;
    boolean finish = false;

    public void setFinish(boolean finish) {
        this.finish = finish;
    }

    public boolean getFinish(){
        return finish;
    }

    private void setTokens(int numberOfTokens, int maxValueForAToken){
        for (int i = 0; i < numberOfTokens; i++){
            Token newToken = new Token();
            Random random = new Random();
            newToken.setValue(random.nextInt(maxValueForAToken));
            if (existsToken(newToken.getValue())){
                while (existsToken(newToken.getValue())){
                    newToken.setValue(random.nextInt(maxValueForAToken));
                }
            }
            tokens.add(newToken);
            tokensUsed.add(false);
        }
    }

    public void setBoard(int numberOfTokens, int maxValueForAToken, int winningLengthList){
        this.numberOfTokens = numberOfTokens;
        this.maxValueForAToken = maxValueForAToken;
        this.winningLengthList = winningLengthList;
        setTokens(numberOfTokens, maxValueForAToken);
    }

    public boolean existsToken(int findToken){
        for (int i = 0; i < tokens.size(); i++){
            if (findToken == tokens.get(i).getValue()){
                return true;
            }
        }
        return false;
    }

    public int extractRandomToken(){
        Random random = new Random();
        int position = random.nextInt(tokens.size());
        if (tokensUsed.get(position)){
            while (tokensUsed.get(position)){
                position = random.nextInt(tokens.size());
            }
        }
        tokensUsed.set( position, true);
        return tokens.get(position).getValue();
    }

    public boolean drawCondition(){
        for (int i = 0; i < tokensUsed.size(); i++)
            if (!tokensUsed.get(i)){
                return false;
            }
        return true;
    }

    public int getWinningLengthList() {
        return winningLengthList;
    }
}

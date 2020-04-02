public class Game {

    public static void main(String[] args){
        Board board = new Board();
        board.setBoard(10,15,3);
        Runnable player1 = new Player();
        ((Player) player1).setName("Player 1");
        ((Player) player1).setBoard(board);
        new Thread(player1).start();
        Runnable player2 = new Player();
        ((Player) player2).setName("Player 2");
        ((Player) player2).setBoard(board);
        new Thread(player2).start();
    }

}

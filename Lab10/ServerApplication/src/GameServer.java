import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {

    public static final int PORT = 8100;
    public boolean gameOn = true;

    public GameServer() throws IOException{

        ServerSocket serverSocket = null;

        try{
            serverSocket = new ServerSocket(PORT);
            while (true){
                if (!gameOn){
                    break;
                }else {
                    System.out.println("Waiting for a client ...");
                    Socket socket = serverSocket.accept();
                    ClientThread client = new ClientThread(socket, true);
                    client.start();
                    this.gameOn = client.getGameOn();
                }
            }
        } catch (IOException e){
            System.err.println("Ooops... " + e);
        } finally {
            serverSocket.close();

        }
    }

    public static void main(String[] args) throws IOException{
        GameServer server = new GameServer();
    }

}

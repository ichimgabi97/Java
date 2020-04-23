import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket = null;
    public boolean gameOn;

    public ClientThread(Socket socket, boolean gameOn){
        this.socket = socket;
        this.gameOn = gameOn;
    }

    @Override
    public void run(){
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String request = in.readLine();

            PrintWriter out = new PrintWriter(socket.getOutputStream());
            if (request.equals("exit")){
                gameOn = false;
                String raspuns = "Server stopped";
                out.println(raspuns);
                out.flush();
            }else {
                String raspuns = "Server received the request " + request;
                out.println(raspuns);
                out.flush();
            }
        }catch (IOException e){
            System.err.println("Communication error... " + e);
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                System.err.println(e);
            }
        }
    }

    public boolean getGameOn(){
        return gameOn;
    }
}

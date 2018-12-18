import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
/*
class ServerConnect extends Thread{
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;
    public ServerConnect(Socket socket) throws IOException{
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run(){

    }
}
*/

public class Server {
    private static Socket serverSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;
    private static final int PORT = 8080;
    public static void main(String[] args) {
        ArrayList messages = new ArrayList();
        int min = 0;
        int max = 0;

        String message;
        int number;
        try {
            server = new ServerSocket(PORT);
            System.out.println("Server: Started");
            serverSocket = server.accept(); //.accept() is waiting for connection from client
            System.out.println(server.getLocalPort());
            out = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream()));
            in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
            while (true) {
                message = in.readLine();
                number = Integer.parseInt(message);
                System.out.println("Server: Received a new message from client: " + message);
                if (number == 0) {
                    break;
                }
                if (min == 0 & max == 0) {
                    min = number;
                    max = number;
                } else {
                    if (number > max) {
                        max = number;
                    }
                    if (number < min) {
                        min = number;
                    }
                }
            }
            System.out.println("Server: Sending to client max = " + max + " and min = " + min + " to client");
            out.write("Max number is " + max + ", min number is " + min);
            out.flush();
        }
            catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                serverSocket.close();
                in.close();
                out.close();
                server.close();
                System.out.println("Server: Connection is closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
   }
}

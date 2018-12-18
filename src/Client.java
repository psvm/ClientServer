import java.io.*;
import java.net.Socket;

import static java.lang.Thread.sleep;

public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {
        String message;
        String serverResponce;
        try {
            System.out.println("Client: Started");
            clientSocket = new Socket("localhost", 8080);
            reader = new BufferedReader(new InputStreamReader(System.in)); // request to server for connection
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            System.out.println("Client: Started connection to sever");
            while (true) {
                System.out.println("Client: Enter your message to server. Enter 0 for interrupting");
                message = reader.readLine();
                out.write(message + "\n");
                out.flush();
                if (Integer.parseInt(message) == 0) {
                    break;
                }
            }
            serverResponce = in.readLine();
            System.out.println("Client: receiving message from server: " + serverResponce);
        } catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            System.out.println("Client: connection is closed");
            try {
                clientSocket.close();
                in.close();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
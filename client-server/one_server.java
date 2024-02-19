package exp2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class one_server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket clientSocket = null;
        BufferedReader in = null;

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("server started. waiting for client..");

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("client connected!");

                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                    String clientMessage = in.readLine();
                    if (clientMessage != null) {
                        System.out.println("client: " + clientMessage);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (clientSocket != null) clientSocket.close();
                    if (in != null) in.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

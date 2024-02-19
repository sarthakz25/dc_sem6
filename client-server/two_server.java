package exp2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class two_server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;

        try {
            serverSocket = new ServerSocket(1234);
            System.out.println("server started. waiting for client..");

            while (true) {
                try {
                    socket = serverSocket.accept();
                    System.out.println("client connected!");

                    bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                    String clientMessage;
                    while (!((clientMessage = bufferedReader.readLine()).equalsIgnoreCase("stop"))) {
                        System.out.println("client: " + clientMessage);
                        System.out.print("reply: ");

                        String serverResponse = getInputFromConsole();
                        bufferedWriter.write(serverResponse);
                        bufferedWriter.newLine(); // new line so client knows when message ends
                        bufferedWriter.flush();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (socket != null) socket.close();
                    if (bufferedReader != null) bufferedReader.close();
                    if (bufferedWriter != null) bufferedWriter.close();
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

    private static String getInputFromConsole() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine().trim();
    }
}

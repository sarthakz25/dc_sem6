package exp2;

import java.io.*;
import java.net.Socket;

public class two_client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader bufferedReader = null;
        BufferedWriter bufferedWriter = null;
        BufferedReader consoleReader = null;

        try {
            socket = new Socket("localhost", 1234);
            consoleReader = new BufferedReader(new InputStreamReader(System.in));

            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            System.out.print("message: ");
            String messageToSend = consoleReader.readLine().trim();
            bufferedWriter.write(messageToSend);
            bufferedWriter.newLine();
            bufferedWriter.flush();

            String serverMessage = "";
            while (!serverMessage.equalsIgnoreCase("stop")) {
                serverMessage = bufferedReader.readLine();  // Read server message line by line.
                System.out.println("server: " + serverMessage);
                System.out.print("reply: ");
                messageToSend = consoleReader.readLine().trim();
                bufferedWriter.write(messageToSend);
                bufferedWriter.newLine();  // Again, send a newline.
                bufferedWriter.flush();

                if (messageToSend.equalsIgnoreCase("stop")) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (consoleReader != null) {
                    consoleReader.close();
                }
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

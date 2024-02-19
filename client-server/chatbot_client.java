package exp2;

import java.io.*;
import java.net.*;

public class chatbot_client {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 8888);
            BufferedReader userReader = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter outToServer = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader serverAnswer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                System.out.print("what would you like to ask? (type 'exit' to quit) ");
                String userQuery = userReader.readLine();

                if ("exit".equalsIgnoreCase(userQuery)) {
                    break;
                }

                outToServer.println(userQuery);
                System.out.println("server replies: " + serverAnswer.readLine());
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package exp2;

import java.io.*;
import java.net.*;

public class chatbot_server {

    private static final int PORT = 8888;

    public static void main(String[] args) {
        try (ServerSocket serverSock = new ServerSocket(PORT)) {
            System.out.println("server started. waiting for client..");

            while (true) {
                try (Socket connSocket = serverSock.accept()) {
                    System.out.println("client connected!");

                    try (
                            BufferedReader clientReader = new BufferedReader(new InputStreamReader(connSocket.getInputStream()));
                            PrintWriter outToClient = new PrintWriter(connSocket.getOutputStream(), true);
                    ) {
                        String msgFromClient;
                        while ((msgFromClient = clientReader.readLine()) != null && !"exit".equalsIgnoreCase(msgFromClient)) {
                            System.out.println("client says: " + msgFromClient);
                            String serverReply = provideAnswer(msgFromClient);
                            outToClient.println("chat-bot: " + serverReply);
                        }
                    }
                }
                System.out.println("client disconnected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String provideAnswer(String question) {
        question = question.toLowerCase().trim();

        if (question.contains("color") && question.contains("apple")) {
            return "Apples can come in various colors, including red, yellow, and green.";
        } else if (question.contains("color") && question.contains("sky")) {
            return "The sky is usually blue on a clear day but can be grey when it's cloudy.";
        } else if (question.contains("hello") || question.contains("hi")) {
            return "Hello there! How can I assist you today?";
        } else if (question.contains("tall") && question.contains("mount everest")) {
            return "Mount Everest is about 8,848 meters (29,029 feet) above sea level.";
        } else if (question.contains("albert einstein")) {
            return "Albert Einstein was a theoretical physicist known for developing the theory of relativity.";
        } else {
            return "Sorry, I do not understand the question.";
        }
    }
}

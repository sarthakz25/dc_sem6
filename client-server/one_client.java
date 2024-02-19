package exp2;

import java.io.*;
import java.net.Socket;

public class one_client {
    public static void main(String[] args) {
        Socket socket = null;
        BufferedWriter out = null;

        try {
            socket = new Socket("localhost", 1234);

            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("message: ");
            String messageToSend = consoleReader.readLine().trim();

            out.write(messageToSend);
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
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

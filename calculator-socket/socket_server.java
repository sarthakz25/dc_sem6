package exp6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class socket_server {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(6363);
             Socket s = ss.accept();
             DataInputStream din = new DataInputStream(s.getInputStream());
             DataOutputStream dout = new DataOutputStream(s.getOutputStream())) {

            String str;
            String str2;
            while (true) {
                str = din.readUTF();
                if ("goodbye".equalsIgnoreCase(str.trim())) {
                    str2 = "connection is now closed.";
                    dout.writeUTF(str2);
                    dout.flush();
                    break;
                }
                System.out.println("\nclient said: " + str);

                str2 = calculate(str);
                System.out.print("-> server says: " + str2);
                dout.writeUTF(str2);
                dout.flush();
            }
        } catch (IOException e) {
            System.out.println("io error occurred: " + e.getMessage());
        }
    }

    static String calculate(String str) {
        String[] input = str.split(" ");
        if (input.length != 3) {
            return "invalid input format! input format should be: num1 <operator> num2";
        }
        try {
            int num1 = Integer.parseInt(input[0]);
            int num2 = Integer.parseInt(input[2]);
            switch (input[1]) {
                case "+":
                    return String.valueOf(num1 + num2);
                case "-":
                    return String.valueOf(num1 - num2);
                case "*":
                case "x":
                    return String.valueOf(num1 * num2);
                case "/":
                    if (num2 == 0) {
                        return "cannot divide by zero!";
                    }
                    return String.valueOf(num1 / num2);
                default:
                    return "invalid operator! valid operators are + - * /";
            }
        } catch (NumberFormatException e) {
            return "invalid number format!";
        }
    }
}

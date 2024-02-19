package exp6;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class socket_client {
    public static void main(String[] args) throws Exception {
        Socket s = new Socket("localhost", 6363);

        DataInputStream din = new DataInputStream(s.getInputStream());
        DataOutputStream dout = new DataOutputStream(s.getOutputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2 = "";
        System.out.print(">> CALCULATOR <<\ninput format should be: num1 <operator> num2\n");
        while (!str.contains("goodbye")) {
            System.out.print("-> enter an expression: ");
            str = br.readLine();
            dout.writeUTF(str);
            dout.flush();
            str2 = din.readUTF();
            System.out.println("server said: " + str2);
        }

        dout.close();
        s.close();
    }
}

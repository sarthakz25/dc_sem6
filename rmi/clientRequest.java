package exp3;

import java.rmi.*;

public class clientRequest {
    public static void main(String[] args) {
        String answer, value = "Remote Method Invocation in Java";
        try {
            // lookup method to find reference of remote object
            search access =
                    (search) Naming.lookup("rmi://localhost:1234" +
                            "/dc_exp3");
            answer = access.query(value);
            System.out.println("Hello world " + value +
                    " " + answer + " this is Sarthak");
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }
}

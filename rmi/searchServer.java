package exp3;

import java.rmi.*;
import java.rmi.registry.*;

public class searchServer {
    public static void main(String[] args) {
        try {
            // to create an object
            search obj = new searchQuery();

            // rmiregistry within the server JVM with
            // port number 1234
            LocateRegistry.createRegistry(1234);

            // to bind the remote object by the name
            Naming.rebind("rmi://localhost:1234" +
                    "/dc_exp3", obj);
        } catch (Exception ae) {
            System.out.println(ae);
        }
    }
}

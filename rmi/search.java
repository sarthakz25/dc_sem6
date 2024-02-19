package exp3;

import java.rmi.*;

public interface search extends Remote {
    // to declare method prototype
    public String query(String search) throws RemoteException;
}

package exp3;

import java.rmi.*;
import java.rmi.server.*;

public class searchQuery extends UnicastRemoteObject
        implements search {
    // default constructor to throw RemoteException from parent constructor
    searchQuery() throws RemoteException {
        super();
    }

    // implementation of query interface
    public String query(String search)
            throws RemoteException {
        String result;
        if (search.equals("Remote Method Invocation in Java"))
            result = "Found";
        else
            result = "Not Found";

        return result;
    }
}

import java.rmi.*;
import java.rmi.server.*;

public class HelloMessage extends UnicastRemoteObject
        implements helloInterface{

    public HelloMessage() throws RemoteException {
        //There is no action need in this moment.
    }
    
    public String message() throws RemoteException {
        return ("Hello");
    }
}
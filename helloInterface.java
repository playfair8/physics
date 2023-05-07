import java.rmi.*;

public interface helloInterface extends Remote{
	
	public String message() throws RemoteException;

}
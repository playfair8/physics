import java.rmi.*;

public class ServerCall {

    private static final String host = "localhost";

    public static void main(String[] args) throws Exception {
        //** Step 1
        //** Declare a reference for the object that will be implemented         
        HelloMessage temp = new HelloMessage();
        
        //** Step 2
        //** Declare a string variable for holding the URL of the object's name        
        String rmiObjectName = "rmi://" + host + "/Hello";
        
        //Step 3
        //Binding the object reference to the object name.
        Naming.rebind(rmiObjectName, temp);
        
        //Step 4
        //Tell to the user that the process is completed.        
        System.out.println("Binding complete...\n");
    }
}

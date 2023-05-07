import java.net.*;
import java.util.Scanner;

public class MulticastSender {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("230.0.0.0"); // define the multicast group
            MulticastSocket socket = new MulticastSocket(); // create a multicast socket

            Scanner scanner = new Scanner(System.in); // create a scanner object to read user input
            String message = "";

            while (!message.equals("exit")) {
                System.out.print("Enter message to send to group: ");
                message = scanner.nextLine();

                byte[] buffer = message.getBytes(); // convert message to byte array
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, 1234); // create a datagram packet with the message and multicast group information

                socket.send(packet); // send the packet to the group
            }

            socket.close(); // close the socket when finished
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

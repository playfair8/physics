import java.net.*;

public class MulticastReceiver {
    public static void main(String[] args) {
        try {
            InetAddress group = InetAddress.getByName("230.0.0.0"); // define the multicast group
            MulticastSocket socket = new MulticastSocket(1234); // create a multicast socket with the same port number as the sender

            socket.joinGroup(group); // join the multicast group

            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            while (true) {
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received message from " + packet.getAddress() + ": " + message);

                if (message.equals("exit")){
                    socket.leaveGroup(group); // leave the multicast group
                    socket.close(); // close the socket when finished
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

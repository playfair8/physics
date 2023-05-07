import java.util.*;

public class SuzukiKasami {
    private static final int N = 5; // number of processes
    private static boolean[] token = new boolean[N]; // token holder
    private static boolean[] request = new boolean[N]; // request queue
    private static boolean[] ack = new boolean[N]; // acknowledgement queue
    private static int counter = 0; // counter for critical section entry
    private static int holder = 0; // current token holder

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the process id (0-" + (N-1) + "): ");
        int pid = sc.nextInt();

        while (true) {
            System.out.println("Enter 1 to enter critical section, 0 to exit: ");
            int choice = sc.nextInt();
            if (choice == 1) {
                request[pid] = true;
                while (true) {
                    // send request message to next process
                    sendMessage(pid, (pid + 1) % N, "REQUEST");

                    // wait for acknowledgements from all other processes
                    boolean allAck = true;
                    for (int i = 0; i < N; i++) {
                        if (i == pid) continue;
                        if (!ack[i]) {
                            allAck = false;
                            break;
                        }
                    }
                    if (allAck) {
                        // grant permission to enter critical section
                        token[pid] = true;
                        request[pid] = false;
                        holder = pid;
                        counter++;
                        System.out.println("Process " + pid + " entered critical section (counter = " + counter + ")");
                        break;
                    }
                }
            } else {
                // release token and exit critical section
                token[pid] = false;
                holder = (pid + 1) % N;
                counter = 0;
                System.out.println("Process " + pid + " exited critical section");
                // send acknowledgement messages to all other processes
                for (int i = 0; i < N; i++) {
                    if (i == pid) continue;
                    if (request[i]) {
                        sendMessage(pid, i, "ACK");
                        ack[i] = false;
                    }
                }
            }
        }
    }

    private static void sendMessage(int src, int dest, String type) {
        // simulate message passing
        if (type.equals("REQUEST")) {
            System.out.println("Process " + src + " sent REQUEST to process " + dest);
        } else if (type.equals("ACK")) {
            System.out.println("Process " + src + " sent ACK to process " + dest);
        }
        // simulate message receipt
        if (type.equals("REQUEST")) {
            receiveRequest(src, dest);
        } else if (type.equals("ACK")) {
            receiveAck(src, dest);
        }
    }

    private static void receiveRequest(int src, int dest) {
        // add request to queue
        request[src] = true;
        // if token is held, send acknowledgement
        if (token[dest]) {
            sendMessage(dest, src, "ACK");
        }
    }

    private static void receiveAck(int src, int dest) {
        // mark acknowledgement as received
        ack[src] = true;
    }
}

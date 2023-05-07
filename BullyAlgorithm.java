import java.util.Scanner;

public class BullyAlgorithm{

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the length of the array: ");
        int length = s.nextInt();
        int[] nodes = new int[length];
        int[] state = new int[length];
        System.out.print("\nEnter the elements of the array: ");

        for (int i = 0; i < length; i++) {
            nodes[i] = s.nextInt();
        }
        System.out.print("\nEnter the state of the nodes: ");

        for (int i = 0; i < length; i++) {
            state[i] = s.nextInt();
        }

        System.out.print("\nEnter the discovering node: ");
        int start = s.nextInt();

        int max = 0, count = 0;

        for(int i = 0; i < nodes.length; i++){
            if(nodes[start] < nodes[i]){
                count++;
            }
        }

        while(count > 0){
            for(int i = 0; i < nodes.length; i++){
                if(state[start] == 1 && nodes[start] < nodes[i]){
                    System.out.println("Node " + nodes[start] + " sends message to Node " + nodes[i]);
                }
            }
            System.out.println("");

            for(int i = 0; i < nodes.length; i++){
                if(state[start] == 1 && state[i] == 1 && nodes[start] < nodes[i]){
                    System.out.println("Node " + nodes[i] + " replies with OK to Node " + nodes[start]);

                    max= Math.max(max, nodes[i]);
                }
            }
            count--;
            start++;
        }
        
        System.out.println("\n" + max + " Is the new Cordinator");
       s.close();
    }
}
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BerkeleyAlgorithm {
    public static void berkeleyAlgo(String serverTime, String clientTime1, String clientTime2) {
        System.out.println("Server Clock   = " + serverTime);
        System.out.println("Client Clock 1 = " + clientTime1);
        System.out.println("Client Clock 2 = " + clientTime2);

        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss");
        try {
            /* Converting time to Milliseconds */
            long s = sdf.parse(serverTime).getTime();
            long t1 = sdf.parse(clientTime1).getTime();
            long t2 = sdf.parse(clientTime2).getTime();

            /* Calcuating time differences w.r.t server */
            long dt1 = t1 - s;
            long dt2 = t2 - s;

            /* Fault tolerant Average */
            long avg = (dt1 + dt2 + 0) / 3;
            System.out.println("\nFault Tolerance Average = " + avg/1000);
            
            /* Adjustment */
            long adjserver = avg;
            long adj_t1 = avg-dt1;
            long adj_t2 = avg-dt2;

            System.out.println("\nServer clock adjustment = " + adjserver/1000);
            System.out.println("Client 1 adjustment = " + adj_t1/1000);
            System.out.println("Client 2 adjustment = " + adj_t2/1000);
            /* sync clock */
            System.out.println("\nSynchronized Server Clock  = " + sdf.format(new Date(s+adjserver)));
            System.out.println("Synchronized Client1 Clock = " + sdf.format(new Date(t1+adj_t1)));
            System.out.println("Synchronized Client2 Clock = " + sdf.format(new Date(t2+adj_t2)));
        } 
        catch (ParseException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        berkeleyAlgo("03:00", "03:25", "02:50");
    }
}
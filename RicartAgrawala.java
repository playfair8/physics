import java.util.ArrayList;
import java.util.Scanner;

public class RicartAgrawala implements Runnable {
	Thread t;
	static ArrayList<Process> order = new ArrayList<Process>();;

	public RicartAgrawala() {
		t = new Thread(this);
		t.start();
	}

	public void run() { // processes enter critical section
		Process p;
		while (true) {
			if (!(RicartAgrawala.order.isEmpty())) {
				p = RicartAgrawala.order.get(0);
				if (p.pen_queue.isEmpty()) {
					p.cs_flag = true;
					p.req_flag = false;
					System.out.println(p.name + " ENTERING INTO CRITICAL SECTION");
					try {
						Thread.sleep(4000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(p.name + " EXITS FROM CRITICAL SECTION");
					p.cs_flag = false;
					for (Process h : p.req_queue) { // process giving reply message to pending requests
						h.Reply(p);
					}
					RicartAgrawala.order.remove(0);
				}
			} else {
				try {
					while (RicartAgrawala.order.isEmpty())
						Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	public static void main(String[] args) throws InterruptedException {
		RicartAgrawala r = new RicartAgrawala();
		int cnt = 1;
		Process p;
		System.out.println("ENTER NAME OF REQUESTING PROCESSES IN ORDER TO ENTER CRITICAL SECTION");
		while (true) {
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			p = new Process(s);
			RicartAgrawala.order.add(p);
			for (Process t : RicartAgrawala.order) { // processes requesting for critical section execution
				if (!t.cs_flag) {
					for (Process v : RicartAgrawala.order) {
						if (!(t.equals(v))) {
							t.Request(v, cnt);
						}
					}
				}
				cnt++;
			}

		}

	}

}
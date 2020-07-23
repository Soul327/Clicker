package Main;

import java.util.Scanner;

public class Commands extends Thread{
	//public void onCommand(CommandSender sender, Command command, String alias, String[] args){}
	public static String onCommand(String[] args) {
		String re = "No command found";
		if(args.length>=1)
			if(args[0].equalsIgnoreCase("help")) {
				if(args.length==1) {
					re ="help <command>\n";
				}
				if(args.length==2) {}
			}
		
		return re;
	}
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.print("User > ");
			String line = scanner.nextLine();
			System.out.println(onCommand( line.split(" ")) );
		}
	}
	public static void main(String args[]) {
		new Commands().start();
		//int y = 0; for(int x=100; x>y; y++ ) System.out.println(x);
	}
}

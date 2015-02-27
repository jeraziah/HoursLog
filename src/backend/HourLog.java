package backend;

import java.util.Scanner;

public class HourLog {
	
	public static void main(String args[]){
		System.out.println("Please enter employeee id number for manager:\n\n");
		
		Scanner scanner = new Scanner(System.in);
		
		//get the employee id number
		int id = scanner.nextInt();
		
		Manager manager = new Manager(id);
		
		System.out.println("Welcome to the hour tracking software of the past! "
				+ "Where all your dreams die and data gets destroyed.");
		
		
		scanner.close();
		return;
	}
}

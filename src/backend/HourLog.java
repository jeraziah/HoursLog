package backend;

import java.util.Scanner;

public class HourLog {
	public static Database db;
	
	public static void main(String args[]){	
		System.out.println("Please enter employeee id number for manager:");
		
		Scanner scanner = new Scanner(System.in);
		
		//get the employee id number
		int id = scanner.nextInt();
		
		User currentUser = new Manager(id);
		
		System.out.println("Welcome to the hour tracking software of the past! "
				+ "Where all your dreams die and data gets destroyed.");
		
		db = new Database();
		db.putUser(currentUser);
		
		System.out.println("Enter your user id:");
		
		
		scanner.close();
		return;
	}
	
	public User identifyUser(int id)  {
		return db.pullUser(id);
	}
	
	public boolean addEmployee(User user, int id) {
		boolean userAccepted = false;
		
		if(user.isManager()) {
			userAccepted = db.putUser(user);
			if(userAccepted) {
				return true;
			}
		}
		
		return false;
	}

}

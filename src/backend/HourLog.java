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
	
	public boolean setPayScale(int id, int hourlyRate) {
		User user = db.pullUser(id);
		
		if(user == null) {
			return false;
		}
		
		if(user instanceof Employee) {
			((Employee) user).payScale = hourlyRate;
			return true;
		}
		if(user instanceof Manager) {
			((Manager) user).payScale = hourlyRate;
			return true;
		}
		
		return false;
	}

}

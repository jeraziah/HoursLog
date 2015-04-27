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
		
		System.out.println("Please enter a command or type 'h' for help:\n");
		
		String command = scanner.next();
		
		if(command == "h"){
			System.out.println(""
					+ "\nHelp Menu:\n"
					+ "\nh - help"
					+ "\ngetYTD - get year to date earnings of an employee"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n"
					+ "\n");
		}
		else if(command == "getYTD"){
			System.out.println("please enter employee id to get YTD of:\n");
			int tempId = scanner.nextInt();
			User tempUser = new Employee(tempId);
			System.out.println(tempUser.getYTD());
		}
		else if(command == "")
		
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
	
	public boolean removeEmployee(int id) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
			return false;
		}
		else {
			user = null;
			db.putUser(user);
			return true;
		}
	}
	
	public boolean viewDailyHours(int id, int month, int dayOfMonth) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
		}
		
		if(user instanceof Employee) {
			((Employee) user).getDailyHours(month, dayOfMonth);
			return true;
		}
		if(user instanceof Manager) {
			((Manager) user).getDailyHours(month, dayOfMonth);
			return true;
		}
		
		return false;
	}
	
	public boolean viewWeeklyHours(int id, int month, int firstDayOfWeek) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
		}
		
		if(user instanceof Employee) {
			((Employee) user).getWeeklyHours(month, firstDayOfWeek);
			return true;
		}
		if(user instanceof Manager) {
			((Manager) user).getWeeklyHours(month, firstDayOfWeek);
			return true;
		}
		
		return false;
	}
	
	public boolean approveHours(int id) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
		}
		
		if(user instanceof Employee) {
			return ((Employee) user).approveHours();
		}
		
		return false;
	}
	
	public boolean viewSick(int id) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
		}
		
		if(user instanceof Employee) {
			return ((Employee) user).viewSick();
		}
		
		return false;
	}
	
	public boolean viewVacation(int id) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
		}
		
		if(user instanceof Employee) {
			return ((Employee) user).viewVacation();
		}
		
		return false;
	}
	
	public boolean checkApproved(int id) {
		User user = db.pullUser(id);
		
		if(user == null) {
			System.err.println("User does not exist.");
		}
		
		if(user instanceof Employee) {
			if(((Employee) user).approved() == true) { return true; }
			else { return false; }
		}
		
		return false;
	}
}
